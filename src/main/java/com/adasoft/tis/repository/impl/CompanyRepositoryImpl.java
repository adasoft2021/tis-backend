package com.adasoft.tis.repository.impl;

import com.adasoft.tis.core.repository.AbstractTisRepository;
import com.adasoft.tis.domain.Company;
import com.adasoft.tis.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Collection;

@Repository
public class CompanyRepositoryImpl extends AbstractTisRepository<Company, Long> implements CompanyRepository {
    @Autowired
    protected CompanyRepositoryImpl(final EntityManager entityManager) {
        super(entityManager, Company.class);
    }

    @Override
    public Collection<Company> getAll() {
        String jpqlQuery = "SELECT a FROM Company a";

        return entityManager.createQuery(jpqlQuery, Company.class)
            .getResultList();
    }

    @Override
    public boolean existName(String name) {
        String query = String.format("SELECT CASE WHEN COUNT(e) > 0 THEN TRUE ELSE FALSE END " +
            "FROM %s e " +
            "WHERE e.name = :name ", Company.class.getSimpleName());
        return entityManager.createQuery(query, Boolean.class)
            .setParameter("name", name).getSingleResult();
    }

    @Override
    public Collection<Company> getSemesterCompanies(String semester, Long adviserId) {
        String jpqlQuery = "SELECT c FROM Company c where c.semester.semester = :semester " +
            "and c.adviser.id = :adviserId";

        return entityManager.createQuery(jpqlQuery, Company.class)
            .setParameter("semester", semester)
            .setParameter("adviserId", adviserId)
            .getResultList();
    }
}
