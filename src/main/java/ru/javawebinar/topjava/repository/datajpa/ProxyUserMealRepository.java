package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by VPortianko on 10.07.2015.
 */
public interface ProxyUserMealRepository extends JpaRepository<UserMeal, Integer> {

    @Override
    @Transactional
    UserMeal save(UserMeal userMeal);

    UserMeal findByIdAndUser_Id(Integer id, Integer userId);


////    @Modifying
////    @Transactional
////    @Query("DELETE FROM UserMeal m WHERE m.id=:id")
////    int delete(@Param("id") int id);

    @Transactional
    int deleteByIdAndUser_Id(Integer id, Integer userId);

    @Transactional
    void deleteByUser_Id(Integer userId);

    List<UserMeal> findByUser_IdOrderByDateTimeDesc(Integer userId);

    List<UserMeal> findByUser_IdAndDateTimeBetweenOrderByDateTimeDesc(Integer userId, LocalDateTime start, LocalDateTime end);
}
