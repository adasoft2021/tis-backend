package com.adasoft.tis.repository.impl;

import com.adasoft.tis.core.repository.AbstractTisRepository;
import com.adasoft.tis.domain.BaseQualification;
import com.adasoft.tis.repository.BaseQualificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Collection;

@Repository
public class BaseQualificationRepositoryImpl extends AbstractTisRepository<BaseQualification, Long>
    implements BaseQualificationRepository {
    @Autowired
    protected BaseQualificationRepositoryImpl(final EntityManager entityManager) {
        super(entityManager, BaseQualification.class);
    }

    @Override
    public Collection<BaseQualification> getAll() {
        String jpqlQuery = "SELECT q FROM BaseQualification q";

        return entityManager.createQuery(jpqlQuery, BaseQualification.class)
            .getResultList();
    }
}
