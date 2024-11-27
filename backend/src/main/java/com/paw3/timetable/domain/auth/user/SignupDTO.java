package com.paw3.timetable.domain.auth.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class SignupDTO {
    @NonNull
    private String email;

    @NonNull
    private String password;

    @NonNull
    private String role;

    private Long studentGroupId;
}
