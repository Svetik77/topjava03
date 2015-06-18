package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

/**
 * GKislin
 * 15.06.2015.
 */
public interface UserMealService {

    UserMeal save(UserMeal userMeal);

    void delete(int id, int userId) throws NotFoundException;

    UserMeal get(int id, int userId) throws NotFoundException;

    List<UserMeal> getByUserId(int userId);

    void update(UserMeal userMeal, int UserId) throws NotFoundException;

    List<UserMeal> getByUserIdDateRange(int userId, LocalDateTime from, LocalDateTime to);

    void deleteAll(int userId);
}
