package com.adasoft.tis.repository.impl;

import com.adasoft.tis.core.repository.AbstractTisRepository;
import com.adasoft.tis.domain.Partner;
import com.adasoft.tis.repository.PartnerRepository;

import javax.persistence.EntityManager;

public class PartnerRepositoryImpl extends AbstractTisRepository<Partner, Long> implements PartnerRepository {
    protected PartnerRepositoryImpl(final EntityManager entityManager) {
        super(entityManager, Partner.class);
    }
}
