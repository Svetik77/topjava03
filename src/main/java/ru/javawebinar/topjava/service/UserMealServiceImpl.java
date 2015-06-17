package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.exception.ExceptionUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Service
public class UserMealServiceImpl implements UserMealService {

    @Autowired
    private UserMealRepository repository;

    @Override
    public UserMeal save(UserMeal userMeal) {
        return repository.save(userMeal);
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        UserMeal userMeal = ExceptionUtil.check(repository.get(id), id);
        ExceptionUtil.checkIfMealBelongsToUser(userMeal, userId);
        ExceptionUtil.check(repository.delete(id), id);
    }

    @Override
    public UserMeal get(int id, int userId) throws NotFoundException {
        UserMeal userMeal = ExceptionUtil.check(repository.get(id), id);
        ExceptionUtil.checkIfMealBelongsToUser(userMeal, userId);
        return userMeal;
    }

    @Override
    public List<UserMeal> getByUserId(int userId) {
        return repository.getByUserId(userId);
    }

    @Override
    public void update(UserMeal userMeal, int userId) throws NotFoundException {
        ExceptionUtil.checkIfMealBelongsToUser(userMeal, userId);
        ExceptionUtil.check(repository.save(userMeal), userMeal.getId());
    }
}
