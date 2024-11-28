package com.paw3.timetable.domain.semester;

public class SemesterDateConflictException extends RuntimeException {
    public SemesterDateConflictException(String message) {
        super(message);
    }
}
