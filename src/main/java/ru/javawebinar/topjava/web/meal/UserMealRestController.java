package ru.javawebinar.topjava.web.meal;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.to.UserMealWithExceed;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by VPortianko on 24.07.2015.
 */
@RestController
@RequestMapping(UserMealRestController.REST_URL)
public class UserMealRestController extends AbstractUserMealController {
    static final String REST_URL = "/rest/meals";
    static final String APPLICATION_JSON_UTF8 = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8";

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON_UTF8)
    public UserMeal get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON_UTF8)
    public List<UserMealWithExceed> getAll() {
        return super.getAll();
    }

    @Override
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteAll() {
        super.deleteAll();
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody UserMeal meal, @PathVariable("id") int id) {
        super.update(meal,id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_UTF8)
    public ResponseEntity<UserMeal> createWithLocation(@RequestBody UserMeal meal) {
        UserMeal created = super.create(meal);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uriOfNewResource);

        return new ResponseEntity<>(created, httpHeaders, HttpStatus.CREATED);
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON_UTF8,
            params = {"startDate","endDate","startTime","endTime"})
    public List<UserMealWithExceed> getBetween(
            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("startTime") LocalTime startTime,
            @RequestParam("endDate") LocalDate endDate,
            @RequestParam("endTime")LocalTime endTime) {
        return super.getBetween(startDate, startTime, endDate, endTime);
    }
}