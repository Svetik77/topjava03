package ru.javawebinar.topjava.web.meal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.LoggedUser;
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

    public List<UserMeal> getAllMeal() {
        int userId = LoggedUser.id();
        LOG.info(String.format("Get all meal for user(id=%d)",userId));
        return service.getByUserId(userId);
    }

    public UserMeal get(int id) {
        int userId = LoggedUser.id();
        LOG.info(String.format("Get meal(id=%d) by user(id=%d)",id,userId));
        return service.get(id,userId);
    }

    public UserMeal create(UserMeal userMeal) {
        int userId = LoggedUser.id();
        UserMeal newUserMeal = service.save(userMeal);
        LOG.info(String.format("Save new meal(id=%d) by user(id=%d)",newUserMeal.getId(),userId));
        return userMeal;
    }

    public void delete(int id) {
        int userId = LoggedUser.id();
        LOG.info(String.format("Delete meal(id=%d) by user(id=%d)", id, userId));
        service.delete(id, userId);
    }

    public void update(UserMeal userMeal) {
        int userId = LoggedUser.id();
        LOG.info(String.format("Update meal(id=%d) by user(id=%d)",userMeal.getId(),userId));
        service.update(userMeal,userId);
    }

}
