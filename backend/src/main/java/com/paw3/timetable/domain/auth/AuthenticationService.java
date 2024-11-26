package com.paw3.timetable.domain.auth;

import com.paw3.timetable.domain.auth.user.User;
import com.paw3.timetable.domain.auth.user.UserDTO;
import com.paw3.timetable.domain.auth.user.UserRepository;
import com.paw3.timetable.domain.student_group.StudentGroup;
import com.paw3.timetable.domain.student_group.StudentGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final StudentGroupRepository studentGroupRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public User signup(UserDTO userDTO) {
        StudentGroup studentGroup = studentGroupRepository.findById(userDTO.getStudentGroupId())
                .orElse(null);

        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setStudentGroup(studentGroup);

        return userRepository.save(user);
    }

    public User authenticate(UserDTO userDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDTO.getEmail(),
                        userDTO.getPassword()
                )
        );

        return userRepository.findByEmail(userDTO.getEmail())
                .orElseThrow();
    }
}