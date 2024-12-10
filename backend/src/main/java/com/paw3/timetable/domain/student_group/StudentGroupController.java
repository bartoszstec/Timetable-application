package com.paw3.timetable.domain.student_group;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StudentGroupController {

    private final StudentGroupService studentGroupService;

    @GetMapping("/groups")
    List<StudentGroup> getAllStudentGroups() {
        return studentGroupService.findAll();
    }

    @GetMapping("/groups/{id}")
    StudentGroup getStudentGroup(@PathVariable Long id) {
        return studentGroupService.findById(id);
    }

    @PostMapping("/groups")
    @PreAuthorize("hasRole('OFFICE')")
    StudentGroup newStudentGroup(@RequestBody StudentGroupDTO studentGroupDTO) {
        return studentGroupService.save(studentGroupDTO);
    }
}
