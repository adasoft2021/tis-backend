package com.adasoft.tis.repository.impl;

import com.adasoft.tis.core.repository.AbstractTisRepository;
import com.adasoft.tis.domain.CompanySpace;
import com.adasoft.tis.domain.Review;
import com.adasoft.tis.domain.Space;
import com.adasoft.tis.repository.CompanySpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CompanySpaceRepositoryImpl extends AbstractTisRepository<CompanySpace, Long> implements CompanySpaceRepository {
    @Autowired
    protected CompanySpaceRepositoryImpl(final EntityManager entityManager) {
        super(entityManager, CompanySpace.class);
    }

    @Override
    public List<Object[]> getCountCompanySpacesByReview(Long companyId) {
        String jpqlQuery = "SELECT a.becauseOf,COUNT(a) FROM CompanySpace a " +
            "WHERE a.company.id = :companyId group by a.becauseOf";

        return entityManager.createQuery(jpqlQuery, Object[].class)
            .setParameter("companyId", companyId)
            .getResultList();
    }

    @Override
    public List<Space> getSpacesByReview(Review r, boolean answered) {
        String jpqlQuery = "SELECT a.space from CompanySpace a WHERE a.becauseOf = :becauseOf " +
            "and a.space.spaceAnswers.size between :inf and :sup order by a.space.id";

        return entityManager.createQuery(jpqlQuery, Space.class)
            .setParameter("becauseOf", r)
            .setParameter("inf", answered ? 1 : 0)
            .setParameter("sup", answered ? Integer.MAX_VALUE : 0)
            .getResultList();
    }

}
