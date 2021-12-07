package com.adasoft.tis.repository;

import com.adasoft.tis.core.repository.TisRepository;
import com.adasoft.tis.domain.Review;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends TisRepository<Review, Long> {
    Collection<Review> findByCompany(Long companyId);

    Collection<Review> findByAdviser(Long adviserId);

    List<Review> findByStatus(Long adviserId, Long projectId, Review.Status status);

    Optional<Review> findByCompanyAndStatus(Long id, Review.Status s);
}
