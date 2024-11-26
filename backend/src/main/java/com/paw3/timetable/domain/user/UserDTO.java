package com.paw3.timetable.domain.user;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
public class UserDTO {
    @NonNull
    private String email;

    @NonNull
    private String password;
}
