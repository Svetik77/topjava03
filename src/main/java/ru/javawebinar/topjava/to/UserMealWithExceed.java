package ru.javawebinar.topjava.to;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */
public class UserMealWithExceed {
    protected Integer id;

    protected LocalDateTime dateTime;

    protected String description;

    protected int calories;

    protected boolean exceed;

    public UserMealWithExceed(Integer id, LocalDateTime dateTime, String description, int calories, boolean exceed) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
    }

    public UserMealWithExceed(UserMeal meal, boolean exceed) {
        this.id = meal.getId();
        this.dateTime = meal.getDateTime();
        this.description = meal.getDescription();
        this.calories = meal.getCalories();
        this.exceed = exceed;
    }

    public UserMealWithExceed() {
    }

    @Override
    public String toString() {
        return "UserMealWithExceed{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", exceed=" + exceed +
                '}';
    }
}
