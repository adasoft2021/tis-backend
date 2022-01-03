package com.adasoft.tis.repository;

import com.adasoft.tis.core.repository.TisRepository;
import com.adasoft.tis.domain.CompanySpace;
import com.adasoft.tis.domain.Review;
import com.adasoft.tis.domain.Space;

import java.util.Collection;
import java.util.List;

public interface CompanySpaceRepository extends TisRepository<CompanySpace, Long> {
    Collection<Object[]> getCountCompanySpacesByReview(Long companyId);

    List<Space> getSpacesByReview(Review r, boolean answered);
}
