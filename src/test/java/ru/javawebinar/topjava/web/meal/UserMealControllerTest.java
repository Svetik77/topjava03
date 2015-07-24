package ru.javawebinar.topjava.web.meal;

import org.junit.Test;
import ru.javawebinar.topjava.MealTestData;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

/**
 * Created by VPortianko on 23.07.2015.
 */
public class UserMealControllerTest extends AbstractUserMealControllerTest {

    @Test
    public void testMealList() throws Exception {
        mockMvc.perform(get("/meals"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("mealList"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/mealList.jsp"))
                .andExpect(model().attribute("mealList", hasSize(6)))
                .andExpect(model().attribute("mealList", hasItem(
                        allOf(
                                hasProperty("id", is(START_SEQ + 2)),
                                hasProperty("description", is(MealTestData.MEAL1.getDescription()))
                        )
                )))
                .andExpect(model().attribute("mealList", hasItem(
                        allOf(
                                hasProperty("calories", is(MealTestData.MEAL5.getCalories())),
                                hasProperty("description", is(MealTestData.MEAL5.getDescription()))
                        )
                )));
    }

//    public void testUserList() throws Exception {
//        mockMvc.perform(get("/users"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(view().name("userList"))
//                .andExpect(forwardedUrl("/WEB-INF/jsp/userList.jsp"))
//                .andExpect(model().attribute("userList", hasSize(2)))
//                .andExpect(model().attribute("userList", hasItem(
//                        allOf(
//                                hasProperty("id", is(START_SEQ)),
//                                hasProperty("name", is(USER.getName()))
//                        )
//                )));
//    }
}
