package com.paw3.timetable.domain.auth.user;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
public class UserDTO {
    @NonNull
    private String email;

    @NonNull
    private String password;

    Long studentGroupId;
}
