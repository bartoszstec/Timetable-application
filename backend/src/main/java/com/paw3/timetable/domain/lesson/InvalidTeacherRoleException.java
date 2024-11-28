package com.paw3.timetable.domain.lesson;

public class InvalidTeacherRoleException extends RuntimeException {
    public InvalidTeacherRoleException(String message) {
        super(message);
    }
}
