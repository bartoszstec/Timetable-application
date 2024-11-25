package com.paw3.timetable.lesson;


import com.paw3.timetable.semester.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> findBySemester(Semester semester);
    List<Lesson> findBySemesterAndOccurrence(Semester semester, Lesson.Occurrence occurrence);
}
