package com.paw3.timetable.lesson;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;

    public List<Lesson> findAll() {
        return lessonRepository.findAll();
    }

    public Lesson findById(Long id) {
        return lessonRepository.findById(id)
                .orElseThrow(() -> new LessonNotFoundException("Lesson of id " + id + " not found"));
    }

    public Lesson save(LessonDTO lessonDTO) {
        return lessonRepository.save(convertToEntity(lessonDTO));
    }

    private Lesson convertToEntity(LessonDTO lessonDTO) {
        Lesson lesson = new Lesson();

        lesson.setName(lessonDTO.getName());
        lesson.setTeacher(lessonDTO.getTeacher());
        lesson.setStudentGroup(lessonDTO.getStudentGroup());
        lesson.setRoom(lessonDTO.getRoom());
        lesson.setStartTime(lessonDTO.getStartTime());
        lesson.setEndTime(lessonDTO.getEndTime());
        lesson.setDayOfTheWeek(DayOfTheWeek.valueOf(lessonDTO.getDayOfTheWeek()));

        return lesson;
    }
}


