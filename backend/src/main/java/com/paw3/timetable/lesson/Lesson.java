package com.paw3.timetable.lesson;

import com.paw3.timetable.semester.Semester;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String teacher;

    @NonNull
    private String studentGroup;

    @NonNull
    private String room;

    @NonNull
    @Column(name = "start_time")
    private LocalTime startTime;

    @NonNull
    @Column(name = "end_time")
    private LocalTime endTime;

    @NonNull
    private DayOfTheWeek dayOfTheWeek;

    @NonNull
    private Occurrence occurrence;

    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "semester_id")
    private Semester semester;

    enum DayOfTheWeek {
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


