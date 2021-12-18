package com.adasoft.tis.repository.impl;

import com.adasoft.tis.core.repository.AbstractTisRepository;
import com.adasoft.tis.domain.Comment;
import com.adasoft.tis.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class CommentRepositoryImpl extends AbstractTisRepository<Comment, Long> implements CommentRepository {
    @Autowired
    protected CommentRepositoryImpl(final EntityManager entityManager) {
        super(entityManager, Comment.class);
    }
}
