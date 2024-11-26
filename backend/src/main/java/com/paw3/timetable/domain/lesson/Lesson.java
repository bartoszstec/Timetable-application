package com.paw3.timetable.domain.lesson;

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

    @NonNull
    private String name;

    @NonNull
    private String teacher;

    @NonNull
    private String room;

    @NonNull
    @Column(name = "start_time")
    private LocalTime startTime;

    @NonNull
    @Column(name = "end_time")
    private LocalTime endTime;

    @NonNull
    @Enumerated(EnumType.STRING)
    private DayOfTheWeek dayOfTheWeek;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Occurrence occurrence;

    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_group_id")
    private StudentGroup studentGroup;

    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "semester_id")
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


