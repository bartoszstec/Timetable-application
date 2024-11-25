package com.paw3.timetable.semester;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SemesterController {

    private final SemesterService semesterService;

    @GetMapping("/semesters")
    List<Semester> all() {
        return semesterService.findAll();
    }

    @GetMapping("/semesters/{id}")
    Semester one(@PathVariable Long id) {
        return semesterService.findById(id);
    }

    @PostMapping("/semesters")
    Semester newSemester(@RequestBody SemesterDTO semesterDTO) {
        return semesterService.save(semesterDTO);
    }

    @DeleteMapping("/semesters/{id}")
    void deleteSemester(@PathVariable Long id) {
        semesterService.deleteById(id);
    }
}
