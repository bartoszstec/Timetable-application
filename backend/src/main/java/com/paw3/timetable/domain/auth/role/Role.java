package com.paw3.timetable.domain.auth.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.paw3.timetable.domain.auth.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    @JsonIgnore
    private Date createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    @JsonIgnore
    private Date updatedAt;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<User> users;

    public enum RoleEnum {
        STUDENT,
        TEACHER,
        OFFICE
    }
}
