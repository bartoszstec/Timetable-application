package com.paw3.timetable.exception;

import com.paw3.timetable.lesson.LessonNotFoundException;
import com.paw3.timetable.semester.SemesterNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({LessonNotFoundException.class, SemesterNotFoundException.class})
    public ResponseEntity<ErrorDetails> handleNotFoundException(RuntimeException e, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}
