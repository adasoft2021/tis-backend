package com.adasoft.tis.repository.impl;

import com.adasoft.tis.core.repository.AbstractTisRepository;
import com.adasoft.tis.domain.Observation;
import com.adasoft.tis.repository.ObservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ObservationRepositoryImpl extends AbstractTisRepository<Observation, Long> implements ObservationRepository {
    @Autowired
    protected ObservationRepositoryImpl(final EntityManager entityManager) {
        super(entityManager, Observation.class);
    }

    @Override
    public List<Observation> getAllByReviewId(Long reviewId) {
        String jpqlQuery = "SELECT q FROM Observation q WHERE q.review.id = :reviewId";

        return entityManager.createQuery(jpqlQuery, Observation.class)
            .setParameter("reviewId", reviewId)
            .getResultList();
    }
}
