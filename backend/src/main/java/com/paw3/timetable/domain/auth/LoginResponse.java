package com.paw3.timetable.domain.auth;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class LoginResponse {
    @NonNull
    private String token;

    @NonNull
    private Long expiresIn;

    @NonNull
    private String role;
}
