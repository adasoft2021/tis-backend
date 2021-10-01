package com.adasoft.tis.repository.impl;

import com.adasoft.tis.core.repository.AbstractTisRepository;
import com.adasoft.tis.domain.Review;
import com.adasoft.tis.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class ReviewRepositoryImpl extends AbstractTisRepository<Review, Long> implements ReviewRepository {
    @Autowired
    protected ReviewRepositoryImpl(final EntityManager entityManager) {
        super(entityManager, Review.class);
    }
}
