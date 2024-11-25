package com.paw3.timetable.lesson;

import com.paw3.timetable.semester.Semester;
import com.paw3.timetable.semester.SemesterNotFoundException;
import com.paw3.timetable.semester.SemesterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;
    private final SemesterRepository semesterRepository;

    public List<Lesson> findAll() {
        return lessonRepository.findAll();
    }

    public Lesson findById(Long id) {
        return lessonRepository.findById(id)
                .orElseThrow(() -> new LessonNotFoundException("Lesson of id " + id + " not found."));
    }

    public Lesson save(LessonDTO lessonDTO) {
        return lessonRepository.save(convertToEntity(lessonDTO));
    }

    public void deleteById(Long id) {
        lessonRepository.deleteById(id);
    }

    private Lesson convertToEntity(LessonDTO lessonDTO) {
        Semester semester = semesterRepository.findById(lessonDTO.getSemesterId())
                .orElseThrow(() -> new SemesterNotFoundException("Semester not found with id: " + lessonDTO.getSemesterId()));

        return new Lesson(
                lessonDTO.getName(),
                lessonDTO.getTeacher(),
                lessonDTO.getStudentGroup(),
                lessonDTO.getRoom(),
                lessonDTO.getStartTime(),
                lessonDTO.getEndTime(),
                Lesson.DayOfTheWeek.valueOf(lessonDTO.getDayOfTheWeek().toUpperCase()),
                Lesson.Occurrence.valueOf(lessonDTO.getOccurrence().toUpperCase()),
                semester
        );
    }

}


