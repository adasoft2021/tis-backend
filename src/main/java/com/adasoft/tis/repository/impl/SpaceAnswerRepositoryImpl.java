package com.adasoft.tis.repository.impl;

import com.adasoft.tis.core.repository.AbstractTisRepository;
import com.adasoft.tis.domain.SpaceAnswer;
import com.adasoft.tis.repository.SpaceAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Collection;

@Repository
public class SpaceAnswerRepositoryImpl extends AbstractTisRepository<SpaceAnswer, Long>
    implements SpaceAnswerRepository {
    @Autowired
    protected SpaceAnswerRepositoryImpl(final EntityManager entityManager) {
        super(entityManager, SpaceAnswer.class);
    }

    @Override
    public Collection<SpaceAnswer> getBySpaceId(final Long spaceId) {
        String jpqlQuery = "SELECT s FROM SpaceAnswer s WHERE s.space.id = :spaceId";

        return entityManager.createQuery(jpqlQuery, SpaceAnswer.class)
            .setParameter("spaceId", spaceId)
            .getResultList();
    }

    @Override
    public Collection<SpaceAnswer> findCompanyAnswers(Long companyId, Long spaceId) {
        String jpqlQuery = "SELECT s FROM SpaceAnswer s WHERE s.space.id = :spaceId and s.createdBy.id = :companyId";

        return entityManager.createQuery(jpqlQuery, SpaceAnswer.class)
            .setParameter("companyId", companyId)
            .setParameter("spaceId", spaceId)
            .getResultList();
    }
}
