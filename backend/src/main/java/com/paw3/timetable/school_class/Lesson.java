package com.paw3.timetable.school_class;

import jakarta.persistence.*;
import lombok.*;


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
    private String gang;

    @NonNull
    private String room;

    @NonNull
    @Column(name = "start_time")
    private String startTime;

    @NonNull
    @Column(name = "end_time")
    private String endTime;

}
