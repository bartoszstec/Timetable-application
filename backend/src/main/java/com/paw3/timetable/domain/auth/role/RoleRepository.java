package com.paw3.timetable.domain.auth.role;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName(Role.RoleEnum name);
}
