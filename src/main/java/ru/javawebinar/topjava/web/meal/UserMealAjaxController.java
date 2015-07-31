package ru.javawebinar.topjava.web.meal;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.to.UserMealWithExceed;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by VPortianko on 31.07.2015.
 */
@RestController
@RequestMapping("/ajax/meals")
public class UserMealAjaxController extends AbstractUserMealController {

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserMealWithExceed> getAll() {
        return super.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void update(@RequestParam("item_id") int id,
                       @RequestParam("description") String description,
                       @RequestParam("calories") int calories,
                       @RequestParam("datetime") LocalDateTime dateTime) {

        UserMeal meal = new UserMeal(id,dateTime,description,calories);
        if (id == 0) {
            meal.setId(null);
            super.create(meal);
        } else {
            super.update(meal, id);
        }
    }
}
