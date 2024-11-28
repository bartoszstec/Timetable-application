package com.paw3.timetable.domain.auth;

import com.paw3.timetable.domain.auth.user.LoginDTO;
import com.paw3.timetable.jwt.JwtService;
import com.paw3.timetable.domain.auth.user.User;
import com.paw3.timetable.domain.auth.user.SignupDTO;
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
    public User register(@RequestBody SignupDTO signupDTO) {
        return authenticationService.signup(signupDTO);
    }

    @PostMapping("/login")
    public LoginResponse authenticate(@RequestBody LoginDTO loginDTO) {
        User authenticatedUser = authenticationService.authenticate(loginDTO);

        String jwtToken = jwtService.generateToken(authenticatedUser);
        String role = authenticatedUser.getRole();

        return new LoginResponse(
                jwtToken,
                jwtService.getExpirationTime(),
                role
        );
    }

}
