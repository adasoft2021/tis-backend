package com.adasoft.tis.repository;

import com.adasoft.tis.core.repository.TisRepository;
import com.adasoft.tis.domain.BaseQualification;

import java.util.Collection;

public interface BaseQualificationRepository extends TisRepository<BaseQualification, Long> {
    Collection<BaseQualification> getAll();
}
