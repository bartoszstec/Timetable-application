package com.paw3.timetable.lesson.exception;

public class LessonTimeConflictException extends RuntimeException {
    public LessonTimeConflictException(String message) {
        super(message);
    }
}
