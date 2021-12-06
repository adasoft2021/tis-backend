package com.adasoft.tis.repository;

import com.adasoft.tis.core.repository.TisRepository;
import com.adasoft.tis.domain.Semester;

import java.util.List;
import java.util.Optional;

public interface SemesterRepository extends TisRepository<Semester, Long> {
    Optional<Semester> getNow();

    Optional<Semester> findBySemester(String semester);

    List<Semester> getAll();
}
