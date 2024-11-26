package com.paw3.timetable.domain.student_group;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentGroupDTO {

    @NonNull
    private String name;
}
