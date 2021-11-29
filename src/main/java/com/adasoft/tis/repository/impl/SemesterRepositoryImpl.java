package com.adasoft.tis.repository.impl;

import com.adasoft.tis.core.repository.AbstractTisRepository;
import com.adasoft.tis.domain.Semester;
import com.adasoft.tis.repository.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class SemesterRepositoryImpl extends AbstractTisRepository<Semester, Long> implements SemesterRepository {

    @Autowired
    protected SemesterRepositoryImpl(final EntityManager entityManager) {
        super(entityManager, Semester.class);
    }

    @Override
    public Optional<Semester> getNow() {
        Optional<Semester> response = Optional.empty();
        String query = "SELECT s FROM Semester s WHERE s.now = :true";
        Semester foundSemester = entityManager.createQuery(query, Semester.class).getSingleResult();
        if (foundSemester != null) {
            response = Optional.of(foundSemester);
        }
        return response;
    }

    @Override
    public Optional<Semester> findBySemester(String semester) {
        Optional<Semester> response = Optional.empty();
        String query = "SELECT s FROM Semester s WHERE s.semester = :semester";
        Semester foundSemester = entityManager.createQuery(query, Semester.class)
            .setParameter("semester", semester).getSingleResult();
        if (foundSemester != null) {
            response = Optional.of(foundSemester);
        }
        return response;
    }
}
