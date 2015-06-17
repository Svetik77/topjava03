package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

/**
 * GKislin
 * 15.06.2015.
 */
public interface UserMealService {

    UserMeal save(UserMeal userMeal);

    void delete(int id, User user) throws NotFoundException;

    UserMeal get(int id, User user) throws NotFoundException;

    List<UserMeal> getByAllUser(User user);

    void update(UserMeal userMeal, User user) throws NotFoundException;
}
