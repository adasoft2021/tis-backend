package com.adasoft.tis.repository;

import com.adasoft.tis.core.repository.TisRepository;
import com.adasoft.tis.domain.Review;

import java.util.Collection;

public interface ReviewRepository extends TisRepository<Review, Long> {
    Collection<Review> findByCompany(Long companyId);

    Collection<Review> findByAdviser(Long adviserId);
}
