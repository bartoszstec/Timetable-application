package com.paw3.timetable.domain.lesson;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @GetMapping("/lessons")
    List<Lesson> all() {
        return lessonService.findAll();
    }

    @GetMapping("/lessons/{id}")
    Lesson one(@PathVariable Long id) {
        return lessonService.findById(id);
    }

    @PostMapping("/lessons")
    @PreAuthorize("hasRole('OFFICE')")
    Lesson newLesson(@RequestBody LessonDTO lessonDTO) {
        return lessonService.save(lessonDTO);
    }

    @DeleteMapping("/lessons/{id}")
    @PreAuthorize("hasRole('OFFICE')")
    ResponseEntity<String> deleteLesson(@PathVariable Long id) {
        return lessonService.deleteById(id);
    }

}
