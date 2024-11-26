package com.paw3.timetable.domain.semester;

import com.paw3.timetable.domain.lesson.Lesson;
import com.paw3.timetable.domain.lesson.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SemesterService {
    
    private final SemesterRepository semesterRepository;
    private final LessonRepository lessonRepository;


    public List<Semester> findAll() {
        return semesterRepository.findAll();
    }

    public Semester findById(Long id) {
        return semesterRepository.findById(id)
                .orElseThrow(() -> new SemesterNotFoundException("Semester of id " + id + " not found"));
    }

    public List<Lesson> findLessonsBySemesterId(Long id) {
        Semester semester = semesterRepository.findById(id)
                .orElseThrow(() -> new SemesterNotFoundException("Semester of id " + id + " not found"));

        return lessonRepository.findBySemester(semester);
    }

    public List<Lesson> findLessonsWithOccurrenceBySemesterId(Long id, Lesson.Occurrence occurrence) {
        Semester semester = semesterRepository.findById(id)
                .orElseThrow(() -> new SemesterNotFoundException("Semester of id " + id + " not found"));

        return lessonRepository.findBySemesterAndOccurrence(semester, occurrence);
    }

    public Semester save(SemesterDTO semesterDTO) {
        return semesterRepository.save(convertToEntity(semesterDTO));
    }

    public ResponseEntity<String> deleteById(Long id) {
        if (semesterRepository.deleteAndFetch(id) == null) {
            throw new SemesterNotFoundException("Semester of id " + id + " not found");
        }

        return new ResponseEntity<>("Semester of id " + id + " has been deleted", HttpStatus.OK);
    }

    private Semester convertToEntity(SemesterDTO semesterDTO) {
        Semester semester = new Semester();

        semester.setStartDate(semesterDTO.getStartDate());
        semester.setEndDate(semesterDTO.getEndDate());
        semester.setName(semesterDTO.getName());

        return semester;
    }

}
