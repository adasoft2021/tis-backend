package com.adasoft.tis.repository;

import com.adasoft.tis.core.repository.TisRepository;
import com.adasoft.tis.domain.ClassCode;

import java.util.Optional;

public interface ClassCodeRepository extends TisRepository<ClassCode, Long> {
    boolean existByCode(String code);
    Optional<ClassCode> getByCode(String code);
    Optional<ClassCode> findByAdviserId(Long adviserId);
}

