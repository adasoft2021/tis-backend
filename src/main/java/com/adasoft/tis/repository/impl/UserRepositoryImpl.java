package com.adasoft.tis.repository.impl;

import com.adasoft.tis.core.repository.AbstractTisRepository;
import com.adasoft.tis.domain.User;
import com.adasoft.tis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class UserRepositoryImpl extends AbstractTisRepository<User, Long> implements UserRepository {
    @Autowired
    protected UserRepositoryImpl(final EntityManager manager) {
        super(manager, User.class);
    }
}
