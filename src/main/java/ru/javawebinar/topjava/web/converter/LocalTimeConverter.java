package ru.javawebinar.topjava.web.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by VPortianko on 24.07.2015.
 */
public class LocalTimeConverter implements Converter<String, LocalTime> {

    private String pattern;


    @Override
    public LocalTime convert(String s) {
        return LocalTime.parse(s, DateTimeFormatter.ofPattern(pattern));
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
