package com.adasoft.tis.repository;

import com.adasoft.tis.core.repository.TisRepository;
import com.adasoft.tis.domain.Observation;

import java.util.List;

public interface ObservationRepository extends TisRepository<Observation, Long> {
    List<Observation> getAllByReviewId(Long reviewId);
}
