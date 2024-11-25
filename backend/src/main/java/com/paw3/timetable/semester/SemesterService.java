package com.paw3.timetable.semester;

import com.paw3.timetable.lesson.Lesson;
import com.paw3.timetable.lesson.LessonRepository;
import lombok.RequiredArgsConstructor;
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
                .orElseThrow(() -> new SemesterNotFoundException("Semester of id " + id + " not found."));
    }

    public List<Lesson> findLessonsBySemesterId(Long semesterId) {
        Semester semester = semesterRepository.findById(semesterId)
                .orElseThrow(() -> new SemesterNotFoundException("Semester not found with id: " + semesterId));

        return lessonRepository.findBySemester(semester);
    }

    public List<Lesson> findLessonsWithOccurrenceBySemesterId(Long semesterId, Lesson.Occurrence occurrence) {
        Semester semester = semesterRepository.findById(semesterId)
                .orElseThrow(() -> new SemesterNotFoundException("Semester not found with id: " + semesterId));

        return lessonRepository.findBySemesterAndOccurrence(semester, occurrence);
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
