package com.adasoft.tis.repository.impl;

import com.adasoft.tis.core.repository.AbstractTisRepository;
import com.adasoft.tis.domain.Review;
import com.adasoft.tis.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.List;

@Repository
public class ReviewRepositoryImpl extends AbstractTisRepository<Review, Long> implements ReviewRepository {
    @Autowired
    protected ReviewRepositoryImpl(final EntityManager entityManager) {
        super(entityManager, Review.class);
    }

    @Override
    public Collection<Review> findByCompany(Long companyId) {

        String jpqlQuery = "SELECT r FROM Review r WHERE r.company.id = :companyId " +
            "and r.deleted = false and r.status >= :s";

        return entityManager.createQuery(jpqlQuery, Review.class)
            .setParameter("companyId", companyId)
            .setParameter("s", Review.Status.CHANGE_ORDER)
            .getResultList();

    }

    @Override
    public Collection<Review> findByAdviser(Long adviserId) {
        String jpqlQuery = "SELECT r FROM Review r WHERE r.createdBy.id = :adviserId and r.deleted = false";

        return entityManager.createQuery(jpqlQuery, Review.class)
            .setParameter("adviserId", adviserId)
            .getResultList();
    }

    @Override
    public List<Review> findByStatus(Long adviserId, Long projectId, Review.Status status) {
        String jpqlQuery = "SELECT r FROM Review r WHERE r.createdBy.id = :adviserId and r.deleted = false " +
            "and r.company.project.id = :projectId and r.status = :status";

        return entityManager.createQuery(jpqlQuery, Review.class)
            .setParameter("adviserId", adviserId)
            .setParameter("projectId", projectId)
            .setParameter("status", status)
            .getResultList();
    }

    @Override
    public Collection<Review> findByCompanyAll(Long companyId) {
        String jpqlQuery = "SELECT r FROM Review r WHERE r.company.id = :companyId and r.deleted = false";

        return entityManager.createQuery(jpqlQuery, Review.class)
            .setParameter("companyId", companyId)
            .getResultList();
    }
}
