package com.paw3.timetable.domain.auth;

import com.paw3.timetable.domain.auth.role.Role;
import com.paw3.timetable.domain.auth.role.RoleNotFoundException;
import com.paw3.timetable.domain.auth.role.RoleRepository;
import com.paw3.timetable.domain.auth.user.*;
import com.paw3.timetable.domain.student_group.StudentGroup;
import com.paw3.timetable.domain.student_group.StudentGroupNotFoundException;
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
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public User signup(SignupDTO signupDTO) {
        User user = new User();

        if (signupDTO.getStudentGroupId() != null) {
            StudentGroup studentGroup = studentGroupRepository.findById(signupDTO.getStudentGroupId())
                    .orElseThrow(() -> new StudentGroupNotFoundException("Student group of id " + signupDTO.getStudentGroupId() + " not found"));

            user.setStudentGroup(studentGroup);
        }

        Role role = roleRepository.findByName(Role.RoleEnum.valueOf(signupDTO.getRole().toUpperCase()))
                .orElseThrow(() -> new RoleNotFoundException("Role " + signupDTO.getRole() + " not found"));

        user.setEmail(signupDTO.getEmail());
        user.setPassword(passwordEncoder.encode(signupDTO.getPassword()));
        user.setFirstName(signupDTO.getFirstName());
        user.setLastName(signupDTO.getLastName());
        user.setRole(role);

        return userRepository.save(user);
    }

    public User authenticate(LoginDTO loginDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getEmail(),
                        loginDTO.getPassword()
                )
        );

        return userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User of email " + loginDTO.getEmail() + " not found"));
    }
}