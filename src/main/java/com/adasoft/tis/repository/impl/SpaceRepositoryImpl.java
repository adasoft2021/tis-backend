package com.adasoft.tis.repository.impl;

import com.adasoft.tis.core.repository.AbstractTisRepository;
import com.adasoft.tis.domain.Space;
import com.adasoft.tis.repository.SpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Collection;

@Repository
public class SpaceRepositoryImpl extends AbstractTisRepository<Space, Long>
    implements SpaceRepository {
    @Autowired
    protected SpaceRepositoryImpl(final EntityManager entityManager) {
        super(entityManager, Space.class);
    }

    @Override
    public Collection<Space> findByAdviser(Long adviserId, Space.SpaceType spaceType) {
        String jpqlQuery = "SELECT s FROM Space s WHERE s.createdBy.id = :adviserId";
        if (!spaceType.equals(Space.SpaceType.ALL))
            jpqlQuery += " and s.spaceType = :spaceType";
        return entityManager.createQuery(jpqlQuery, Space.class)
            .setParameter("adviserId", adviserId)
            .setParameter("spaceType", spaceType)
            .getResultList();
    }
}
