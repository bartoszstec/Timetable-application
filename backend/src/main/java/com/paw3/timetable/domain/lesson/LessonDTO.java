package com.paw3.timetable.domain.lesson;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@RequiredArgsConstructor
public class LessonDTO {
    @NonNull
    private String name;

    @NonNull
    private String teacher;

    @NonNull
    private String studentGroup;

    @NonNull
    private String room;

    @NonNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime startTime;

    @NonNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime endTime;

    @NonNull
    private String dayOfTheWeek;

    @NonNull
    private String occurrence;

    @NonNull
    @Column(name = "semester_id")
    private Long semesterId;
}
