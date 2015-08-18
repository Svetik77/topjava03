package ru.javawebinar.topjava.util;

import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * GKislin
 * 07.01.2015.
 */
public class TimeUtil {
    public static final DateTimeFormatter DATE_TME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public static String toString(LocalDateTime ldt) {
        return toString(ldt, DATE_TME_FORMATTER);
    }

    public static String toString(LocalDateTime ldt, DateTimeFormatter formatter) {
        return ldt == null ? "" : ldt.format(formatter);
    }

    public static LocalDateTime toDateTime(String str) {
        return toDateTime(str, DATE_TME_FORMATTER);
    }

    public static LocalDateTime toDateTime(String str, DateTimeFormatter formatter) {
        return StringUtils.isEmpty(str) ? LocalDateTime.now() : LocalDateTime.parse(str, formatter);
    }

    public static boolean isBetween(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) <= 0;
    }

    public static LocalDate toLocalDate(String string) {
        return LocalDate.parse(string, DATE_FORMATTER);
    }

    public static LocalTime toLocalTime(String string) {
        return LocalTime.parse(string, TIME_FORMATTER);
    }
}
