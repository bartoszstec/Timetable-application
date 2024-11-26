package com.paw3.timetable.domain.lesson;

import com.paw3.timetable.domain.semester.Semester;
import com.paw3.timetable.domain.semester.SemesterNotFoundException;
import com.paw3.timetable.domain.semester.SemesterRepository;
import com.paw3.timetable.domain.student_group.StudentGroup;
import com.paw3.timetable.domain.student_group.StudentGroupNotFoundException;
import com.paw3.timetable.domain.student_group.StudentGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;
    private final SemesterRepository semesterRepository;
    private final StudentGroupRepository studentGroupRepository;

    public List<Lesson> findAll() {
        return lessonRepository.findAll();
    }

    public Lesson findById(Long id) {
        return lessonRepository.findById(id)
                .orElseThrow(() -> new LessonNotFoundException("Lesson of id " + id + " not found"));
    }

    public Lesson save(LessonDTO lessonDTO) {
        Semester semester = semesterRepository.findById(lessonDTO.getSemesterId())
                .orElseThrow(() -> new SemesterNotFoundException("Semester of id " + lessonDTO.getSemesterId() + " not found"));

        StudentGroup studentGroup = studentGroupRepository.findById(lessonDTO.getStudentGroupId())
                .orElseThrow(() -> new StudentGroupNotFoundException("Student group of id " + lessonDTO.getStudentGroupId() + " not found"));

        List<Lesson> lessons = lessonRepository.findBySemesterAndDayOfTheWeekAndStudentGroup(
                semester,
                Lesson.DayOfTheWeek.valueOf(lessonDTO.getDayOfTheWeek().toUpperCase()),
                studentGroup
        );

        LocalTime startTime = lessonDTO.getStartTime();
        LocalTime endTime = lessonDTO.getEndTime();
        String occurrence = lessonDTO.getOccurrence().toLowerCase();

        for (Lesson lesson: lessons) {
            if (timeRangesOverlap(startTime, endTime, lesson.getStartTime(), lesson.getEndTime())) {
                boolean isTimeConflict = "all".equals(occurrence) ||
                        ("odd".equals(occurrence) && ("odd".equalsIgnoreCase(lesson.getOccurrence().toString()) || "all".equalsIgnoreCase(lesson.getOccurrence().toString()))) ||
                        ("even".equals(occurrence) && ("even".equalsIgnoreCase(lesson.getOccurrence().toString()) || "all".equalsIgnoreCase(lesson.getOccurrence().toString())));

                if (isTimeConflict) {
                    throw new LessonTimeConflictException("Lesson time collides with an existing lesson from "
                            + lesson.getStartTime() + " to " + lesson.getEndTime());
                }
            }
        }

        return lessonRepository.save(convertToEntity(lessonDTO, semester, studentGroup));
    }

    private boolean timeRangesOverlap(LocalTime start1, LocalTime end1, LocalTime start2, LocalTime end2) {
        return start1.isBefore(end2) && start2.isBefore(end1);
    }

    public ResponseEntity<String> deleteById(Long id) {
        if (lessonRepository.deleteAndFetch(id) == null) {
            throw new LessonNotFoundException("Lesson of id " + id + " not found");
        }
        return new ResponseEntity<>("Lesson of id " + id + " has been deleted", HttpStatus.OK);
    }

    private Lesson convertToEntity(LessonDTO lessonDTO, Semester semester, StudentGroup studentGroup) {
        Lesson lesson = new Lesson();

        lesson.setName(lessonDTO.getName());
        lesson.setTeacher(lessonDTO.getTeacher());
        lesson.setRoom(lessonDTO.getRoom());
        lesson.setStartTime(lessonDTO.getStartTime());
        lesson.setEndTime(lessonDTO.getEndTime());
        lesson.setDayOfTheWeek(Lesson.DayOfTheWeek.valueOf(lessonDTO.getDayOfTheWeek().toUpperCase()));
        lesson.setOccurrence(Lesson.Occurrence.valueOf(lessonDTO.getOccurrence().toUpperCase()));
        lesson.setStudentGroup(studentGroup);
        lesson.setSemester(semester);

        return lesson;
    }

}


