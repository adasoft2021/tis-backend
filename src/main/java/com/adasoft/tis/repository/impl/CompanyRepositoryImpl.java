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
}
