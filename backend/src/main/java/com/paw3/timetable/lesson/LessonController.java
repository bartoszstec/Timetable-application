package com.paw3.timetable.lesson;

import lombok.RequiredArgsConstructor;
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
    Lesson newLesson(@RequestBody LessonDTO lessonDTO) {
        return lessonService.save(lessonDTO);
    }
}
