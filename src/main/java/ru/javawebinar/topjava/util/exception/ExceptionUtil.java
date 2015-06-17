package ru.javawebinar.topjava.util.exception;


import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;

/**
 * User: gkislin
 * Date: 14.05.2014
 */
public class ExceptionUtil {
    private static final LoggerWrapper LOG = LoggerWrapper.get(ExceptionUtil.class);

    public static void check(boolean found, int id) {
        check(found, "id=" + id);
    }

    public static <T> T check(T object, int id) {
        return check(object, "id=" + id);
    }

    public static <T> T check(T object, String msg) {
        check(object != null, msg);
        return object;
    }

    public static void check(boolean found, String msg) {
        if (!found) throw LOG.getNotFoundException("Not found entity with " + msg);
    }

    public static void checkIfMealBelongsToUser(UserMeal userMeal, int userId) {
        if (userMeal.getUser().getId() != userId)
            throw LOG.getNotFoundException(String.format(
                    "Mismatch between LoggedUser(id=%d) and MealUser(id=%d)"
                    ,userId
                    ,userMeal.getUser().getId())
            );
    }
}
