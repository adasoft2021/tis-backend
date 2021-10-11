package com.adasoft.tis.repository;

import com.adasoft.tis.core.repository.TisRepository;
import com.adasoft.tis.domain.Adviser;

import java.util.Collection;

public interface AdviserRepository extends TisRepository<Adviser, Long> {
    Collection<Adviser> getAll();
}
