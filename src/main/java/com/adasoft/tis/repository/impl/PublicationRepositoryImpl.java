package com.adasoft.tis.repository.impl;

import com.adasoft.tis.core.repository.AbstractTisRepository;
import com.adasoft.tis.domain.Publication;
import com.adasoft.tis.dto.publication.PublicationResponseDTO;
import com.adasoft.tis.repository.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Collection;

@Repository
public class PublicationRepositoryImpl extends AbstractTisRepository<Publication, Long>
    implements PublicationRepository {
    @Autowired
    protected PublicationRepositoryImpl(final EntityManager entityManager) {
        super(entityManager, Publication.class);
    }

    @Override
    public Collection<Publication> getByAdviserId(final Long adviserId, final Publication.PublicationType type) {
        String jpqlQuery = "SELECT p FROM Publication p WHERE p.createdBy.id = :adviserId and p.type = :type and p.deleted = false";

        return entityManager.createQuery(jpqlQuery, Publication.class)
            .setParameter("adviserId", adviserId)
            .setParameter("type", type)
            .getResultList();
    }
    @Override
    public Collection<Publication> getByAdviserIdSemester(Long adviserId, Publication.PublicationType type, String semester){
        String jpqlQuery = "SELECT p FROM Publication p WHERE p.createdBy.id = :adviserId and p.type = :type and p.semester = :semester and p.deleted = false";
        return entityManager.createQuery(jpqlQuery, Publication.class)
            .setParameter("adviserId", adviserId)
            .setParameter("type", type)
            .setParameter("semester", semester)
            .getResultList();
    }
}
