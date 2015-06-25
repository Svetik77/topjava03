package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.BaseEntity;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.MealTestData.*;
/**
 * Created by Vova on 25.06.2015.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMealServiceTest {


    @Autowired
    protected UserMealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void testGet() throws Exception {
        UserMeal userMeal = service.get(BaseEntity.START_SEQ + 2,BaseEntity.START_SEQ);
        MATCHER.assertEquals(MEAL3010,userMeal);
    }

    @Test(expected = NotFoundException.class)
    public void testGetWrongMeal() throws Exception {
        UserMeal userMeal = service.get(BaseEntity.START_SEQ + 2,BaseEntity.START_SEQ + 1);
        MATCHER.assertEquals(MEAL3010,userMeal);
    }

    @Test
    public void testGetAll() throws Exception {
        List<UserMeal> expectedList = new ArrayList<>(MEAL_LIST);
        Collections.sort(expectedList,descCompByDateTime);
        MATCHER.assertListEquals(expectedList,service.getAll(BaseEntity.START_SEQ));
    }

    @Test
    public void testGetBetweenDates() throws Exception {
        LocalDate startDate = LocalDate.of(2015, Month.MAY, 30);
        LocalDate endDate = LocalDate.of(2015, Month.MAY, 30);
        List<UserMeal> expectList = MEAL_LIST.stream()
                .filter(userMeal -> (userMeal.getDateTime().compareTo(LocalDateTime.of(startDate, LocalTime.MIN)) > 0) &&
                            (userMeal.getDateTime().compareTo(LocalDateTime.of(endDate,LocalTime.MAX)) < 0))
                        .sorted(descCompByDateTime)
                        .collect(Collectors.toList());
        MATCHER.assertListEquals(expectList,service.getBetweenDates(startDate, endDate, BaseEntity.START_SEQ));
    }

    @Test
    public void testGetBetweenDateTimes() throws Exception {
        LocalDateTime startTime = LocalDateTime.of(2015, Month.MAY, 31, 9, 0);
        LocalDateTime endTime = LocalDateTime.of(2015, Month.MAY, 31, 21, 0);
        List<UserMeal> expectList = MEAL_LIST.stream()
                .filter(userMeal -> (userMeal.getDateTime().compareTo(startTime) > 0) && (userMeal.getDateTime().compareTo(endTime) < 0))
                .sorted(descCompByDateTime)
                .collect(Collectors.toList());
        MATCHER.assertListEquals(expectList,service.getBetweenDateTimes(startTime,endTime,BaseEntity.START_SEQ));
    }


    @Test
    public void testDelete() throws Exception {
        service.delete(BaseEntity.START_SEQ + 2, BaseEntity.START_SEQ);
        List<UserMeal> expectList = new ArrayList<>(MEAL_LIST);
        expectList.remove(MEAL3010);
        Collections.sort(expectList,descCompByDateTime);
        MATCHER.assertListEquals(expectList,service.getAll(BaseEntity.START_SEQ));
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteWrongUser() throws Exception {
        service.delete(BaseEntity.START_SEQ + 2, BaseEntity.START_SEQ + 1);
        List<UserMeal> expectList = new ArrayList<>(MEAL_LIST);
        expectList.remove(MEAL3010);
        Collections.sort(expectList,descCompByDateTime);
        MATCHER.assertListEquals(expectList,service.getAll(BaseEntity.START_SEQ));
    }

    @Test
    public void testDeleteAll() throws Exception {
        service.deleteAll(BaseEntity.START_SEQ);
        MATCHER.assertListEquals(Collections.emptyList(),service.getAll(BaseEntity.START_SEQ));
    }

    @Test
    public void testUpdate() throws Exception {
        UserMeal updatedMeal = new UserMeal(BaseEntity.START_SEQ + 5, LocalDateTime.of(2015, Month.MAY, 31, 10, 0),"Завтрак",5000);
        service.update(updatedMeal,BaseEntity.START_SEQ);
        MATCHER.assertEquals(updatedMeal,service.get(BaseEntity.START_SEQ + 5,BaseEntity.START_SEQ));
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateWrongUser() throws Exception {
        UserMeal updatedMeal = new UserMeal(BaseEntity.START_SEQ + 5, LocalDateTime.of(2015, Month.MAY, 31, 10, 0),"Завтрак",5000);
        service.update(updatedMeal,BaseEntity.START_SEQ + 1);
        MATCHER.assertEquals(updatedMeal,service.get(BaseEntity.START_SEQ + 5,BaseEntity.START_SEQ + 1));
    }

    @Test
    public void testSave() throws Exception {
        UserMeal newMeal = new UserMeal(null, LocalDateTime.of(2015, Month.MAY, 29, 10, 0),"Завтрак",400);
        UserMeal created = service.save(newMeal, BaseEntity.START_SEQ);
        newMeal.setId(created.getId());
        List<UserMeal> expectedList = new ArrayList<>(MEAL_LIST);
        expectedList.add(newMeal);
        Collections.sort(expectedList,descCompByDateTime);
        MATCHER.assertListEquals(expectedList,service.getAll(BaseEntity.START_SEQ));
    }
}