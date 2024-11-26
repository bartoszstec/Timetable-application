package com.paw3.timetable.domain.student_group;

public class StudentGroupNotFoundException extends RuntimeException {
  public StudentGroupNotFoundException(String message) {
    super(message);
  }
}
