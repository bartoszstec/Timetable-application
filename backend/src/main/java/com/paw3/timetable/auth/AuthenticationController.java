package com.paw3.timetable.auth;

import com.paw3.timetable.jwt.JwtService;
import com.paw3.timetable.domain.user.User;
import com.paw3.timetable.domain.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public User register(@RequestBody UserDTO userDTO) {
        return authenticationService.signup(userDTO);
    }

    @PostMapping("/login")
    public LoginResponse authenticate(@RequestBody UserDTO userDTO) {
        User authenticatedUser = authenticationService.authenticate(userDTO);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        return new LoginResponse(
                jwtToken,
                jwtService.getExpirationTime()
        );
    }

}
