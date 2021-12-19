package com.adasoft.tis.repository.impl;

import com.adasoft.tis.core.repository.AbstractTisRepository;
import com.adasoft.tis.domain.File;
import com.adasoft.tis.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class FileRepositoryImpl extends AbstractTisRepository<File, Long> implements FileRepository {
    @Autowired
    protected FileRepositoryImpl(final EntityManager entityManager) {
        super(entityManager, File.class);
    }
}
