package com.paw3.timetable.domain.auth.user;

import com.paw3.timetable.domain.auth.role.Role;
import com.paw3.timetable.domain.auth.role.RoleNotFoundException;
import com.paw3.timetable.domain.auth.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public List<User> findTeachers() {
        Role teacherRole = roleRepository.findByName(Role.RoleEnum.valueOf("TEACHER"))
                .orElseThrow(() -> new RoleNotFoundException("Teacher role not found"));

        return userRepository.findByRole(teacherRole);
    }
}
