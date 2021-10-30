package com.adasoft.tis.repository.impl;

import com.adasoft.tis.core.repository.AbstractTisRepository;
import com.adasoft.tis.domain.ClassCode;
import com.adasoft.tis.repository.ClassCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class ClassCodeRepositoryImpl extends AbstractTisRepository<ClassCode, Long> implements ClassCodeRepository {

    @Autowired
    protected ClassCodeRepositoryImpl(final EntityManager entityManager) {
        super(entityManager, ClassCode.class);
    }

    @Override
    public boolean existByCode(String code) {
        String query = String.format("SELECT CASE WHEN COUNT(e) > 0 THEN TRUE ELSE FALSE END " +
            "FROM %s e " +
            "WHERE e.code = :code ", ClassCode.class.getSimpleName());
        return entityManager.createQuery(query, Boolean.class)
            .setParameter("code", code).getSingleResult();
    }

    @Override
    public Optional<ClassCode> findByAdviserId(Long adviserId) {
        Optional<ClassCode> response = Optional.empty();
        String query = "SELECT c FROM ClassCode c WHERE c.createdBy.id = :adviserId";
        ClassCode foundClassCode = entityManager.createQuery(query, ClassCode.class)
            .setParameter("adviserId", adviserId).getSingleResult();
        if (foundClassCode != null) {
            response = Optional.of(foundClassCode);
        }
        return response;
    }
}
