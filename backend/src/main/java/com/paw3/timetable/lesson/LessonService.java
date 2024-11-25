package com.paw3.timetable.lesson;

import com.paw3.timetable.lesson.exception.LessonNotFoundException;
import com.paw3.timetable.lesson.exception.LessonTimeConflictException;
import com.paw3.timetable.semester.Semester;
import com.paw3.timetable.semester.SemesterNotFoundException;
import com.paw3.timetable.semester.SemesterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
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
        Semester semester = semesterRepository.findById(lessonDTO.getSemesterId())
                .orElseThrow(() -> new SemesterNotFoundException("Semester of id = " + lessonDTO.getSemesterId() + " not found"));

        List<Lesson> lessons = lessonRepository.findBySemesterAndDayOfTheWeek(semester, Lesson.DayOfTheWeek.valueOf(lessonDTO.getDayOfTheWeek().toUpperCase()));

        LocalTime startTime = lessonDTO.getStartTime();
        LocalTime endTime = lessonDTO.getEndTime();
        String occurrence = lessonDTO.getOccurrence().toLowerCase();

        for (Lesson lesson: lessons) {
            if (timeRangesOverlap(startTime, endTime, lesson.getStartTime(), lesson.getEndTime())) {
                System.out.println("overlap, occurrence = " + occurrence);
                System.out.println("lesson occurrence = " + lesson.getOccurrence());
                boolean isTimeConflict = "all".equals(occurrence) ||
                        ("odd".equals(occurrence) && "odd".equalsIgnoreCase(lesson.getOccurrence().toString())) ||
                        ("even".equals(occurrence) && "even".equalsIgnoreCase(lesson.getOccurrence().toString()));

                if (isTimeConflict) {
                    throw new LessonTimeConflictException("Lesson time collides with an existing lesson from "
                            + lesson.getStartTime() + " to " + lesson.getEndTime());
                }
            }
        }

        return lessonRepository.save(convertToEntity(lessonDTO));
    }

    private boolean timeRangesOverlap(LocalTime start1, LocalTime end1, LocalTime start2, LocalTime end2) {
        return start1.isBefore(end2) && start2.isBefore(end1);
    }

    public void deleteById(Long id) {
        lessonRepository.deleteById(id);
    }

    private Lesson convertToEntity(LessonDTO lessonDTO) {
        Semester semester = semesterRepository.findById(lessonDTO.getSemesterId())
                .orElseThrow(() -> new SemesterNotFoundException("Semester of id = " + lessonDTO.getSemesterId() + " not found"));

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


