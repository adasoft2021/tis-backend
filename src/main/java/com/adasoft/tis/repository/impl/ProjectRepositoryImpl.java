package com.adasoft.tis.repository.impl;

import com.adasoft.tis.core.repository.AbstractTisRepository;
import com.adasoft.tis.domain.Project;
import com.adasoft.tis.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class ProjectRepositoryImpl extends AbstractTisRepository<Project, Long>
        implements ProjectRepository {
    @Autowired
    protected ProjectRepositoryImpl(final EntityManager entityManager) {
        super(entityManager, Project.class);

    }
}
