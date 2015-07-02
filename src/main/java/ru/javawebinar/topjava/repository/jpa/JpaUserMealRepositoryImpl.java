package ru.javawebinar.topjava.repository.jpa;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

/**
 * User: gkislin
 * Date: 26.08.2014
 */

@Repository
@Transactional(readOnly = true)
public class JpaUserMealRepositoryImpl implements UserMealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public UserMeal save(UserMeal userMeal, int userId) {
        if (userMeal.isNew()) {
            User ref = em.getReference(User.class,userId);
            userMeal.setUser(ref);
            em.persist(userMeal);
        } else {
            if (em.createNamedQuery(UserMeal.UPDATE)
                    .setParameter("dateTime",userMeal.getDateTime())
                    .setParameter("description",userMeal.getDescription())
                    .setParameter("calories",userMeal.getCalories())
                    .setParameter("id",userMeal.getId())
                    .setParameter("user_id", userId)
                    .executeUpdate() == 0) return null;
        }
        return userMeal;
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(UserMeal.DELETE).setParameter("id",id).setParameter("user_id",userId).executeUpdate() != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        List<UserMeal> list = em.createNamedQuery(UserMeal.GET,UserMeal.class).setParameter("id",id).setParameter("user_id",userId).getResultList();
        return DataAccessUtils.singleResult(list);
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return em.createNamedQuery(UserMeal.ALL_SORTED_BY_USER,UserMeal.class).setParameter("user_id",userId).getResultList();
    }

    @Override
    @Transactional
    public void deleteAll(int userId) {
        em.createNamedQuery(UserMeal.ALL_DELETE_BY_USER).setParameter("user_id",userId).executeUpdate();
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return em.createNamedQuery(UserMeal.INTERVAL_SORTED_BY_USER,UserMeal.class)
                .setParameter("user_id",userId)
                .setParameter("start",startDate)
                .setParameter("end",endDate)
                .getResultList();
    }
}