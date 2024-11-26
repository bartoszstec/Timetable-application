package com.paw3.timetable.domain.auth;

import com.paw3.timetable.domain.auth.role.Role;
import com.paw3.timetable.domain.auth.role.RoleNotFoundException;
import com.paw3.timetable.domain.auth.role.RoleRepository;
import com.paw3.timetable.domain.auth.user.User;
import com.paw3.timetable.domain.auth.user.UserDTO;
import com.paw3.timetable.domain.auth.user.UserRepository;
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

    public User signup(UserDTO userDTO) {
        User user = new User();

        if (userDTO.getStudentGroupId() != null) {
            StudentGroup studentGroup = studentGroupRepository.findById(userDTO.getStudentGroupId())
                    .orElseThrow(() -> new StudentGroupNotFoundException("Student group of id " + userDTO.getStudentGroupId() + " not found"));

            user.setStudentGroup(studentGroup);
        }

        Role role = roleRepository.findByName(Role.RoleEnum.valueOf(userDTO.getRole().toUpperCase()))
                .orElseThrow(() -> new RoleNotFoundException("Role " + userDTO.getRole() + " not found"));

        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(role);

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