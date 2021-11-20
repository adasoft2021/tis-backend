package com.adasoft.tis.repository;

import com.adasoft.tis.core.repository.TisRepository;
import com.adasoft.tis.domain.Space;

import java.util.Collection;

public interface SpaceRepository extends TisRepository<Space, Long> {
    Collection<Space> findByAdviser(Long adviserId, Space.SpaceType spaceType);
}
