package com.adasoft.tis.repository.impl;

import com.adasoft.tis.core.repository.AbstractTisRepository;
import com.adasoft.tis.domain.CompanySpace;
import com.adasoft.tis.domain.Review;
import com.adasoft.tis.domain.Space;
import com.adasoft.tis.repository.CompanySpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

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

    @Override
    public boolean checkSpacesAnswered(Long spaceId, Long companyId) {
        Optional<CompanySpace> optCs = findBySpace(spaceId, companyId);
        if (optCs.isEmpty()) return false;
        CompanySpace cs = optCs.get();
        Collection<Space> lastSpaces = lastSpaceAssigned(cs.getBecauseOf().getId(), companyId);
        if (lastSpaces.isEmpty()) return false;
        for (Space s : lastSpaces) {
            long answers = s.getSpaceAnswers().stream()
                .filter(spaceAnswer -> !spaceAnswer.isDeleted() &&
                    spaceAnswer.getCreatedBy().getId().equals(cs.getCompany().getId()))
                .count();
            if (answers == 0)
                return false;
        }
        return true;
    }

    @Override
    public Collection<Space> lastSpaceAssigned(Long reviewId, Long companyId) {

        String jpqlQuery2 = "SELECT cs FROM CompanySpace cs " +
            "WHERE cs.becauseOf = :becauseOf and cs.company.id = :companyId";
        return entityManager.createQuery(jpqlQuery2, Space.class)
            .setParameter("becauseOf", reviewId)
            .setParameter("companyId", companyId)
            .getResultList();
    }

    @Override
    public Optional<CompanySpace> findBySpace(Long spaceId, Long companyId) {
        Optional<CompanySpace> res = Optional.empty();
        String jpqlQuery = "SELECT cs FROM CompanySpace cs " +
            "WHERE cs.space.id = :spaceId and cs.company.id = :companyId";

        CompanySpace companySpace = entityManager.createQuery(jpqlQuery, CompanySpace.class)
            .setParameter("spaceId", spaceId)
            .setParameter("companyId", companyId)
            .getSingleResult();
        if (companySpace != null) {
            res = Optional.of(companySpace);
        }
        return res;
    }
}


