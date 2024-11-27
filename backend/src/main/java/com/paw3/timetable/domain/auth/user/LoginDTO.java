package com.paw3.timetable.domain.auth.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class LoginDTO {
    @NonNull
    private String email;

    @NonNull
    private String password;
}
