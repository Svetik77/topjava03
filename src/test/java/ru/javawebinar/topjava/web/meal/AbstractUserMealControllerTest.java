package ru.javawebinar.topjava.web.meal;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.javawebinar.topjava.service.UserMealService;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import static ru.javawebinar.topjava.Profiles.DATAJPA;
import static ru.javawebinar.topjava.Profiles.POSTGRES;

/**
 * Created by VPortianko on 23.07.2015.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-mvc.xml",
        "classpath:spring/spring-db.xml"
})
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ActiveProfiles({POSTGRES, DATAJPA})
public class AbstractUserMealControllerTest {

    protected MockMvc mockMvc;

    @Autowired
    private UserMealService userMealService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @PostConstruct
    void postConstruct() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
}
