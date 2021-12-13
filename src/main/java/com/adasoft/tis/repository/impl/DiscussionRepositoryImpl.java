package com.adasoft.tis.repository.impl;

import com.adasoft.tis.core.repository.AbstractTisRepository;
import com.adasoft.tis.domain.Discussion;
import com.adasoft.tis.repository.DiscussionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class DiscussionRepositoryImpl extends AbstractTisRepository<Discussion, Long> implements DiscussionRepository {
    @Autowired
    protected DiscussionRepositoryImpl(EntityManager manager) {
        super(manager, Discussion.class);
    }
}
