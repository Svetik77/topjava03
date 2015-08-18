package ru.javawebinar.topjava.web.meal;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.exception.UnprocessibleEntityException;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * User: javawebinar.topjava
 */
@RestController
@RequestMapping("/ajax/profile/meals")
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
    public ResponseEntity<String> update(@Valid UserMeal meal, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            result.getFieldErrors().forEach(fe -> sb.append(fe.getField()).append(" ").append(fe.getDefaultMessage()).append("<br>"));
            throw new UnprocessibleEntityException(sb.toString());
        } else {
            if (meal.getId() == 0) {
                super.create(meal);
            } else {
                super.update(meal, meal.getId());
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserMeal get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserMealWithExceed> getFiltered(@RequestParam("startDate") String startDateString,
                                                @RequestParam("endDate") String endDateString,
                                                @RequestParam("startTime") String startTimeString,
                                                @RequestParam("endTime") String endTimeString) {
        LocalDate startDate = StringUtils.isEmpty(startDateString) ? TimeUtil.toLocalDate("0001-01-01") : TimeUtil.toLocalDate(startDateString);
        LocalDate endDate = StringUtils.isEmpty(endDateString) ? TimeUtil.toLocalDate("9999-01-01") : TimeUtil.toLocalDate(endDateString);
        LocalTime startTime = StringUtils.isEmpty(startTimeString) ? LocalTime.MIN : TimeUtil.toLocalTime(startTimeString);
        LocalTime endTime = StringUtils.isEmpty(endTimeString) ? LocalTime.MAX : TimeUtil.toLocalTime(endTimeString);
        return super.getBetween(startDate,startTime,endDate,endTime);
    }

}
