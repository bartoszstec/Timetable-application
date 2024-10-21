package com.paw3.timetable.lesson;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LessonController {

    private final LessonRepository repository;

    @GetMapping("/lessons")
    List<Lesson> all() {
        return repository.findAll();
    }

    @GetMapping("/lessons/{id}")
    Lesson one(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping("/lessons")
    Lesson newLesson(@RequestBody Lesson lesson) {
        return repository.save(lesson);
    }
}
