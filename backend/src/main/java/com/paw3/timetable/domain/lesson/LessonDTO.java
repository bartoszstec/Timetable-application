package com.paw3.timetable.domain.lesson;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class LessonDTO {
    @NonNull
    private String name;

    @NonNull
    private Long teacherId;

    @NonNull
    private Long studentGroupId;

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
    private Long semesterId;
}
