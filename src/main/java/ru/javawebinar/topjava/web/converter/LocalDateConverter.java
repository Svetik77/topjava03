package ru.javawebinar.topjava.web.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by VPortianko on 24.07.2015.
 */
public class LocalDateConverter implements Converter<String, LocalDate> {

    private String pattern;


    @Override
    public LocalDate convert(String s) {
        return LocalDate.parse(s, DateTimeFormatter.ofPattern(pattern));
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
