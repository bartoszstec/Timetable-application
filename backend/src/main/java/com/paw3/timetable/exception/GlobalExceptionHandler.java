package com.paw3.timetable.exception;

import com.paw3.timetable.domain.auth.role.RoleNotFoundException;
import com.paw3.timetable.domain.auth.user.UserNotFoundException;
import com.paw3.timetable.domain.lesson.InvalidTeacherRoleException;
import com.paw3.timetable.domain.lesson.LessonNotFoundException;
import com.paw3.timetable.domain.lesson.LessonTimeConflictException;
import com.paw3.timetable.domain.semester.SemesterDateConflictException;
import com.paw3.timetable.domain.semester.SemesterNotFoundException;
import com.paw3.timetable.domain.student_group.StudentGroupNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            LessonNotFoundException.class,
            SemesterNotFoundException.class,
            StudentGroupNotFoundException.class,
            RoleNotFoundException.class,
            UserNotFoundException.class
    })
    public ResponseEntity<ErrorDetails> handleEntityNotFoundException(RuntimeException e, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({LessonTimeConflictException.class, SemesterDateConflictException.class})
    public ResponseEntity<ErrorDetails> handleConflictException(Exception e, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidTeacherRoleException.class)
    public ResponseEntity<ErrorDetails> handleInvalidRoleException(Exception e, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorDetails> handleGeneralException(Exception e, WebRequest request) {
//        ErrorDetails errorDetails = new ErrorDetails(new Date(), "An unexpected error occurred: " + e.getMessage(), request.getDescription(false));
//        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
