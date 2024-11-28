package com.paw3.timetable.domain.lesson;

import com.paw3.timetable.domain.auth.user.User;
import com.paw3.timetable.domain.semester.Semester;
import com.paw3.timetable.domain.student_group.StudentGroup;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String room;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DayOfTheWeek dayOfTheWeek;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Occurrence occurrence;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    private User teacher;

    @JoinColumn(name = "student_group_id", nullable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    private StudentGroup studentGroup;

    @JoinColumn(name = "semester_id", nullable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    private Semester semester;

    public enum DayOfTheWeek {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }

    public enum Occurrence {
        ALL,
        ODD,
        EVEN
    }
}


