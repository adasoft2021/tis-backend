package com.adasoft.tis.repository.impl;

import com.adasoft.tis.core.repository.AbstractTisRepository;
import com.adasoft.tis.domain.Space;
import com.adasoft.tis.repository.SpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class SpaceRepositoryImpl extends AbstractTisRepository<Space, Long>
    implements SpaceRepository {
    @Autowired
    protected SpaceRepositoryImpl(final EntityManager entityManager) {
        super(entityManager, Space.class);

    }
}

