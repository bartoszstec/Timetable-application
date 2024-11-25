package com.paw3.timetable.semester;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SemesterService {
    
    private final SemesterRepository semesterRepository;


    public List<Semester> findAll() {
        return semesterRepository.findAll();
    }

    public Semester findById(Long id) {
        return semesterRepository.findById(id)
                .orElseThrow(() -> new SemesterNotFoundException("Semester of id " + id + " not found."));
    }

    public Semester save(SemesterDTO semesterDTO) {
        return semesterRepository.save(convertToEntity(semesterDTO));
    }

    private Semester convertToEntity(SemesterDTO semesterDTO) {
        Semester semester = new Semester();

        semester.setStartDate(semesterDTO.getStartDate());
        semester.setEndDate(semesterDTO.getEndDate());

        return semester;
    }

    public void deleteById(Long id) {
        semesterRepository.deleteById(id);
    }
}
