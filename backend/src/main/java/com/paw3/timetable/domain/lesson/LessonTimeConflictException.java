package com.paw3.timetable.domain.lesson;

public class LessonTimeConflictException extends RuntimeException {
    public LessonTimeConflictException(String message) {
        super(message);
    }
}
