package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import javax.sql.DataSource;
import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * User: gkislin
 * Date: 26.08.2014
 */

@Repository
public class JdbcUserMealRepositoryImpl implements UserMealRepository {

    private static final BeanPropertyRowMapper<UserMeal> ROW_MAPPER = new BeanPropertyRowMapper<UserMeal>(UserMeal.class) {
        @Override
        protected void initBeanWrapper(BeanWrapper bw) {
            bw.registerCustomEditor(LocalDateTime.class, new PropertyEditorSupport() {

                @Override
                public LocalDateTime getValue() {
                    return ((Timestamp) super.getValue()).toLocalDateTime();
                }
            });
        }
    };

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertMeal;

    @Autowired
    public JdbcUserMealRepositoryImpl(DataSource dataSource) {
        this.insertMeal = new SimpleJdbcInsert(dataSource)
                .withTableName("MEALS")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", userMeal.getId())
                .addValue("description", userMeal.getDescription())
                .addValue("calories", userMeal.getCalories())
                .addValue("datetime", Timestamp.valueOf(userMeal.getDateTime()))
                .addValue("user_id", userId);
        if (userMeal.isNew()) {
            Number newKey = insertMeal.executeAndReturnKey(map);
            userMeal.setId(newKey.intValue());
        } else {
            int result = namedParameterJdbcTemplate.update(
                    "UPDATE meals SET description=:description, calories=:calories, datetime=:datetime " +
                            "WHERE id=:id AND user_id=:user_id", map);
            if (result == 0) return null;
        }
        return userMeal;
    }

    @Override
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update("DELETE FROM meals WHERE id=? AND user_id=?", id, userId) != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT id, description, calories, datetime FROM meals WHERE id=? AND user_id=?",
                    ROW_MAPPER, id, userId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return jdbcTemplate.query(
                "SELECT id, description, calories, datetime FROM meals WHERE user_id=? ORDER BY datetime DESC",
                ROW_MAPPER,userId);
    }

    @Override
    public void deleteAll(int userId) {
        jdbcTemplate.update("DELETE FROM meals WHERE user_id=?", userId);
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return jdbcTemplate.query(
                "SELECT id, description, calories, datetime FROM meals WHERE datetime > ? AND datetime < ?" +
                        " ORDER BY datetime DESC", ROW_MAPPER, Timestamp.valueOf(startDate),Timestamp.valueOf(endDate));
    }
}
