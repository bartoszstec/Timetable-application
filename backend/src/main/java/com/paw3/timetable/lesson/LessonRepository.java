package com.paw3.timetable.lesson;


import com.paw3.timetable.semester.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> findBySemester(Semester semester);
    List<Lesson> findBySemesterAndOccurrence(Semester semester, Lesson.Occurrence occurrence);
    List<Lesson> findBySemesterAndDayOfTheWeek(Semester semester, Lesson.DayOfTheWeek dayOfTheWeek);

    @Transactional
    default Lesson deleteAndFetch(Long id) {
        Optional<Lesson> entity = findById(id);
        entity.ifPresent(this::delete);
        return entity.orElse(null);
    }
}
