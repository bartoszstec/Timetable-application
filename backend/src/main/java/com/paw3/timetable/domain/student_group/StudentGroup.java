package com.paw3.timetable.domain.student_group;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.paw3.timetable.domain.lesson.Lesson;
import com.paw3.timetable.domain.auth.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class StudentGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "studentGroup", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<User> students;

    @OneToMany(mappedBy = "studentGroup", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Lesson> lessons;
}
