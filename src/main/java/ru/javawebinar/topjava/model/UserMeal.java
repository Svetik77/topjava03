package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */
public class UserMeal extends NamedEntity {
    private LocalDateTime dateTime;

    private int calories;

    private User user;

    public UserMeal() {
    }

    public UserMeal(Integer id, String name, LocalDateTime dateTime, int calories, User user) {
        super(id, name);
        this.dateTime = dateTime;
        this.calories = calories;
        this.user = user;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public int getCalories() {
        return calories;
    }

    @Override
    public String toString() {
        return "UserMeal{" +
                "id=" + id +
                ", user=" + user.getName() +
                ", name=" + name +
                ", date=" + dateTime.toLocalDate() +
                ", calories=" + calories +
                '}';
    }

    public User getUser() {
        return user;
    }
}
