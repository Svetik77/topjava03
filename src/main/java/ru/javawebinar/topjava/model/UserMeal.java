package ru.javawebinar.topjava.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */
@Entity
@Table(name = "MEALS")
@NamedQueries(value = {
        @NamedQuery(name = UserMeal.UPDATE, query = "UPDATE UserMeal SET dateTime=:dateTime,description=:description,calories=:calories " +
                "WHERE id=:id AND user.id=:user_id"),
        @NamedQuery(name = UserMeal.DELETE, query = "DELETE FROM UserMeal m WHERE m.id=:id AND m.user.id=:user_id"),
        @NamedQuery(name = UserMeal.GET, query = "SELECT m FROM UserMeal m WHERE m.id=:id AND m.user.id=:user_id"),
        @NamedQuery(name = UserMeal.ALL_DELETE_BY_USER, query = "DELETE FROM UserMeal m WHERE m.user.id=:user_id"),
        @NamedQuery(name = UserMeal.ALL_SORTED_BY_USER, query = "SELECT m FROM UserMeal m WHERE m.user.id=:user_id ORDER BY m.dateTime desc"),
        @NamedQuery(name = UserMeal.INTERVAL_SORTED_BY_USER, query = "SELECT m FROM UserMeal m WHERE m.user.id=:user_id " +
                "AND m.dateTime>=:start AND m.dateTime<=:end ORDER BY m.dateTime desc"),
})
public class UserMeal extends BaseEntity{

    public static final String UPDATE = "UserMeal.update";
    public static final String DELETE = "UserMeal.delete";
    public static final String GET = "UserMeal.getById";
    public static final String ALL_DELETE_BY_USER = "UserMeal.deleteAll";
    public static final String ALL_SORTED_BY_USER = "UserMeal.getAllByUser";
    public static final String INTERVAL_SORTED_BY_USER = "UserMeal.getIntervalByUser";



    @Column(name = "datetime")
    protected LocalDateTime dateTime;

    @Column(name = "description")
    protected String description;

    @Column(name = "calories")
    protected int calories;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private User user;

    public UserMeal() {
    }

    public UserMeal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Meal(" + id + ", " + dateTime + ", '" + description + "', calories:" + calories + ')';
    }
}
