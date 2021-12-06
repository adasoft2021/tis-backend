package com.adasoft.tis.repository;

import com.adasoft.tis.core.repository.TisRepository;
import com.adasoft.tis.domain.SpaceAnswer;

import java.util.Collection;

public interface SpaceAnswerRepository extends TisRepository<SpaceAnswer, Long> {
    Collection<SpaceAnswer> getBySpaceId(Long spaceId);

    Collection<SpaceAnswer> findCompanyAnswers(Long companyId, Long spaceId);
}
