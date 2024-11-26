package com.paw3.timetable.domain.student_group;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentGroupService {

    private final StudentGroupRepository studentGroupRepository;

    public List<StudentGroup> findAll() {
        return studentGroupRepository.findAll();
    }

    public StudentGroup findById(Long id) {
        return studentGroupRepository.findById(id)
                .orElseThrow(() -> new StudentGroupNotFoundException("Student group of id " + id + " not found"));
    }

    public StudentGroup save(StudentGroupDTO studentGroupDTO) {
        return studentGroupRepository.save(convertToEntity(studentGroupDTO));
    }

//    public ResponseEntity<String> deleteById(Long id) {
//          TODO
//    }

    private StudentGroup convertToEntity(StudentGroupDTO studentGroupDTO) {
       StudentGroup studentGroup = new StudentGroup();

       studentGroup.setName(studentGroupDTO.getName());

       return studentGroup;
    }
}
