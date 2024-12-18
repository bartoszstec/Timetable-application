package com.paw3.timetable.domain.semester;

import com.paw3.timetable.domain.lesson.Lesson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SemesterController {

    private final SemesterService semesterService;

    @GetMapping("/semesters")
    List<Semester> getAllSemesters() {
        return semesterService.findAll();
    }

    @GetMapping("/semesters/{id}")
    Semester getSemester(@PathVariable Long id) {
        return semesterService.findById(id);
    }

    @GetMapping("/semesters/{id}/lessons")
    List<Lesson> getLessonsBySemesterId(@PathVariable Long id, @RequestParam Optional<Lesson.Occurrence> occurrence) {
        if (occurrence.isEmpty()) {
            return semesterService.findLessonsBySemesterId(id);
        } else {
            return semesterService.findLessonsWithOccurrenceBySemesterId(id, occurrence.get());
        }
    }

    @PostMapping("/semesters")
    @PreAuthorize("hasRole('OFFICE')")
    Semester newSemester(@RequestBody SemesterDTO semesterDTO) {
        return semesterService.save(semesterDTO);
    }

    @DeleteMapping("/semesters/{id}")
    @PreAuthorize("hasRole('OFFICE')")
    ResponseEntity<String> deleteSemester(@PathVariable Long id) {
        return semesterService.deleteById(id);
    }
}
