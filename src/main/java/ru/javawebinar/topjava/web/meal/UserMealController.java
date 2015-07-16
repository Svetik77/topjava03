package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.service.UserService;

/**
 * Created by VPortianko on 16.07.2015.
 */
@Controller
public class UserMealController {

    @Autowired
    UserMealService service;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/meals", method = RequestMethod.GET)
    public String mealList(Model model) {
        model.addAttribute("mealList",service.getAll(LoggedUser.id()));
        model.addAttribute("userEmail",userService.get(LoggedUser.id()).getEmail());
        return "mealList";
    }
}
