package com.adasoft.tis.repository.impl;

import com.adasoft.tis.core.repository.AbstractTisRepository;
import com.adasoft.tis.domain.Review;
import com.adasoft.tis.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Collection;

@Repository
public class ReviewRepositoryImpl extends AbstractTisRepository<Review, Long> implements ReviewRepository {
    @Autowired
    protected ReviewRepositoryImpl(final EntityManager entityManager) {
        super(entityManager, Review.class);
    }

    @Override
    public Collection<Review> findByCompany(Long id) {

        String jpqlQuery = "SELECT r FROM Review r WHERE r.company.id = :companyId and r.deleted = false";

        return entityManager.createQuery(jpqlQuery, Review.class)
            .setParameter("companyId", id)
            .getResultList();

    }
}
