package com.paw3.timetable.lesson;

import com.paw3.timetable.semester.Semester;
import com.paw3.timetable.semester.SemesterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;
    private final SemesterService semesterService;

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

    private Lesson convertToEntity(LessonDTO lessonDTO) {
        Semester semester = semesterService.findById(lessonDTO.getSemesterId());

        return new Lesson(
                lessonDTO.getName(),
                lessonDTO.getTeacher(),
                lessonDTO.getStudentGroup(),
                lessonDTO.getRoom(),
                lessonDTO.getStartTime(),
                lessonDTO.getEndTime(),
                DayOfTheWeek.valueOf(lessonDTO.getDayOfTheWeek()),
                semester
        );
    }
}


