package com.adasoft.tis.repository;

import com.adasoft.tis.core.repository.TisRepository;
import com.adasoft.tis.domain.Company;

import java.util.Collection;

public interface CompanyRepository extends TisRepository<Company, Long> {
    Collection<Company> getAll();
    boolean existName(final String name);
}
