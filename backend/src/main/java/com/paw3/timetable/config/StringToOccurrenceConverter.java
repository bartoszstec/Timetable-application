package com.paw3.timetable.config;


import com.paw3.timetable.lesson.Lesson;
import org.springframework.core.convert.converter.Converter;

public class StringToOccurrenceConverter implements Converter<String, Lesson.Occurrence> {
    @Override
    public Lesson.Occurrence convert(String s) {
        return Lesson.Occurrence.valueOf(s.toUpperCase());
    }
}
