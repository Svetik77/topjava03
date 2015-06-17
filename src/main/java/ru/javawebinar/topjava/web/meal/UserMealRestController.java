package ru.javawebinar.topjava.web.meal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;

import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class UserMealRestController {
    private static final LoggerWrapper LOG = LoggerWrapper.get(UserMealRestController.class);

    @Autowired
    private UserMealService service;

    public List<UserMeal> getAll() {
        LOG.info("getAll");
        return service.getAll();
    }

    public List<UserMeal> getByUser(User user) {
        LOG.info("getByUser " + user.getName());
        return service.getByUser(user);
    }

    public UserMeal get(int id) {
        LOG.info("get " + id);
        return service.get(id);
    }

    public UserMeal save(UserMeal userMeal) {
        LOG.info("save " + userMeal);
        return service.save(userMeal);
    }

    public void delete(int id) {
        LOG.info("delete " + id);
        service.delete(id);
    }

    public void update(UserMeal userMeal) {
        LOG.info("update " + userMeal);
        service.update(userMeal);
    }

}
