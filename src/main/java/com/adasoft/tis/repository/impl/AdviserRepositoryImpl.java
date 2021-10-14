package com.adasoft.tis.repository.impl;

import com.adasoft.tis.core.repository.AbstractTisRepository;
import com.adasoft.tis.domain.Adviser;
import com.adasoft.tis.repository.AdviserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Collection;

@Repository
public class AdviserRepositoryImpl extends AbstractTisRepository<Adviser, Long> implements AdviserRepository {
    @Autowired
    protected AdviserRepositoryImpl(final EntityManager entityManager) {
        super(entityManager, Adviser.class);
    }

    @Override
    public Collection<Adviser> getAll() {
        String jpqlQuery = "SELECT a FROM Adviser a";

        return entityManager.createQuery(jpqlQuery, Adviser.class)
            .getResultList();
    }
}
