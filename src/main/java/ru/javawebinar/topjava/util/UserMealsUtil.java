package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        User user = new User();
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(0,"Завтрак",LocalDateTime.of(2015, Month.MAY, 30, 10, 0), 500,user),
                new UserMeal(1,"Обед",LocalDateTime.of(2015, Month.MAY, 30, 10, 0), 1000,user),
                new UserMeal(2,"Ужин",LocalDateTime.of(2015, Month.MAY, 30, 10, 0), 500,user),
                new UserMeal(3,"Завтрак",LocalDateTime.of(2015, Month.MAY, 30, 10, 0), 500,user),
                new UserMeal(4,"Обед",LocalDateTime.of(2015, Month.MAY, 30, 10, 0), 1000,user),
                new UserMeal(5,"Ужин",LocalDateTime.of(2015, Month.MAY, 30, 10, 0), 510,user)
        );
        List<UserMealWithExceed> filteredMealsWithExceeded = getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        filteredMealsWithExceeded.forEach(System.out::println);
    }

    public static List<UserMealWithExceed> getFilteredMealsWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesSumByDate = mealList.stream().collect(Collectors.groupingBy(um -> um.getDateTime().toLocalDate(),
                Collectors.summingInt(UserMeal::getCalories)));

        return mealList.stream()
                .filter(um->TimeUtil.isBetween(um.getDateTime().toLocalTime(), startTime, endTime))
                .map(um->new UserMealWithExceed(um.getDateTime(), um.getName(), um.getCalories(),
                        caloriesSumByDate.get(um.getDateTime().toLocalDate())> caloriesPerDay))
                .collect(Collectors.toList());
    }
}
