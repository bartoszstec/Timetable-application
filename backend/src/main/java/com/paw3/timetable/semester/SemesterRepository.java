package com.paw3.timetable.semester;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Long> {
    @Transactional
    default Semester deleteAndFetch(Long id) {
        Optional<Semester> entity = findById(id);
        entity.ifPresent(this::delete);
        return entity.orElse(null);
    }
}
