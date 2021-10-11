package com.adasoft.tis.repository.impl;

import com.adasoft.tis.core.repository.AbstractTisRepository;
import com.adasoft.tis.domain.Qualification;
import com.adasoft.tis.repository.QualificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class QualificationRepositoryImpl extends AbstractTisRepository<Qualification, Long>
        implements QualificationRepository {
    @Autowired
    protected QualificationRepositoryImpl(final EntityManager entityManager) {
        super(entityManager, Qualification.class);
    }
}
