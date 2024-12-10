package com.paw3.timetable.domain.auth.user;

import com.paw3.timetable.domain.auth.role.RoleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/teachers")
    public List<User> getTeachers() {
        return userService.findTeachers();
    }
}
