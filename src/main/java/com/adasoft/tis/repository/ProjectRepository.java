package com.adasoft.tis.repository;
import com.adasoft.tis.core.repository.TisRepository;
import com.adasoft.tis.domain.Project;

import java.util.List;

public interface ProjectRepository extends TisRepository<Project, Long> {
    List<Project> getAllByAdviserId(Long adviserId);
}
