package ru.javawebinar.topjava.web.meal;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.BaseEntity;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.web.json.JsonUtil;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.MealTestData.WithExceed.*;
import static ru.javawebinar.topjava.web.meal.UserMealRestController.*;


/**
 * Created by VPortianko on 24.07.2015.
 */
public class UserMealRestControllerTest extends AbstractUserMealControllerTest {

    public static final String REST_URL = UserMealRestController.REST_URL + "/";

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + (BaseEntity.START_SEQ + 2)))
                .andDo(print())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(MATCHER.contentMatcher(MEAL1));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + (BaseEntity.START_SEQ + 3)))
                .andDo(print())
                .andExpect(status().isOk());
        MATCHER.assertListEquals(Arrays.asList(MEAL6,MEAL5,MEAL4,MEAL3,MEAL1), userMealService.getAll(LoggedUser.id()));
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MATCHER_EXCEED.contentListMatcher(MEAL6_EXCEED,MEAL5_EXCEED,MEAL4_EXCEED,MEAL3_EXCEED,MEAL2_EXCEED,MEAL1_EXCEED));
    }

    @Test
    public void testDeleteAll() throws Exception {
        mockMvc.perform(delete(REST_URL))
                .andDo(print())
                .andExpect(status().isOk());
        Assert.assertTrue(userMealService.getAll(LoggedUser.id()).isEmpty());
    }

    @Test
    public void testUpdate() throws Exception {
        UserMeal updated = getUpdated();
        mockMvc.perform(put(REST_URL + (BaseEntity.START_SEQ + 2))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isOk());
        MATCHER.assertEquals(updated, userMealService.get(MEAL1_ID,LoggedUser.id()));
    }

    @Test
    public void testCreateWithLocation() throws Exception {
        UserMeal created = getCreated();
        ResultActions actions = mockMvc.perform(post(REST_URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(JsonUtil.writeValue(created)))
                .andDo(print())
                .andExpect(status().isCreated());
        UserMeal returned = MATCHER.fromJsonAction(actions);
        created.setId(returned.getId());
        MATCHER.assertEquals(created, returned);
        MATCHER.assertListEquals(Arrays.asList(created,MEAL6,MEAL5,MEAL4,MEAL3,MEAL2,MEAL1),userMealService.getAll(LoggedUser.id()));
    }

    @Test
    public void testGetBeetween() throws Exception {
        mockMvc.perform(get(REST_URL+"?startDate=31-05-2015&startTime=12-00&endDate=31-05-2015&endTime=22-00"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MATCHER_EXCEED.contentListMatcher(MEAL6_EXCEED, MEAL5_EXCEED));
    }
}