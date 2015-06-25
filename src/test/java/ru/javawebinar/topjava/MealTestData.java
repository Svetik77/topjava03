package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.BaseEntity;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {

    public static final ModelMatcher<UserMeal, String> MATCHER = new ModelMatcher<>(UserMeal::toString);

    /*new UserMeal(1, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
      new UserMeal(2, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
      new UserMeal(3, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
      new UserMeal(4, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 500),
      new UserMeal(5, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 1000),
      new UserMeal(6, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)*/
    public static final UserMeal MEAL3010 =
            new UserMeal(BaseEntity.START_SEQ + 2, LocalDateTime.of(2015, Month.MAY, 30, 10, 0),"Завтрак",500);
    public static final UserMeal MEAL3013 =
            new UserMeal(BaseEntity.START_SEQ + 3, LocalDateTime.of(2015, Month.MAY, 30, 13, 0),"Обед",1000);
    public static final UserMeal MEAL3020 =
            new UserMeal(BaseEntity.START_SEQ + 4, LocalDateTime.of(2015, Month.MAY, 30, 20, 0),"Ужин",500);
    public static final UserMeal MEAL3110 =
            new UserMeal(BaseEntity.START_SEQ + 5, LocalDateTime.of(2015, Month.MAY, 31, 10, 0),"Завтрак",500);
    public static final UserMeal MEAL3113 =
            new UserMeal(BaseEntity.START_SEQ + 6, LocalDateTime.of(2015, Month.MAY, 31, 13, 0),"Обед",1000);
    public static final UserMeal MEAL3120 =
            new UserMeal(BaseEntity.START_SEQ + 7, LocalDateTime.of(2015, Month.MAY, 31, 20, 0),"Ужин",510);
    public static final List<UserMeal> MEAL_LIST = new ArrayList<>();

    public static final Comparator<UserMeal> descCompByDateTime = new Comparator<UserMeal>() {
        @Override
        public int compare(UserMeal o1, UserMeal o2) {
            return o2.getDateTime().compareTo(o1.getDateTime());
        }
    };

    static {
        MEAL_LIST.add(MEAL3010);
        MEAL_LIST.add(MEAL3013);
        MEAL_LIST.add(MEAL3020);
        MEAL_LIST.add(MEAL3110);
        MEAL_LIST.add(MEAL3113);
        MEAL_LIST.add(MEAL3120);
    }

}
