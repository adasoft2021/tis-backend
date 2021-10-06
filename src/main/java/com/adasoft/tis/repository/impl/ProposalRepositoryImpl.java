package com.adasoft.tis.repository.impl;

import com.adasoft.tis.core.repository.AbstractTisRepository;
import com.adasoft.tis.domain.Proposal;
import com.adasoft.tis.repository.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;


@Repository
public class ProposalRepositoryImpl extends AbstractTisRepository<Proposal, Long> implements ProposalRepository {
    @Autowired
    protected ProposalRepositoryImpl(final EntityManager entityManager) {
        super(entityManager, Proposal.class);
    }
}
