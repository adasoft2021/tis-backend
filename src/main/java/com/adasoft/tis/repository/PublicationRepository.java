package com.adasoft.tis.repository;

import com.adasoft.tis.core.repository.TisRepository;
import com.adasoft.tis.domain.Publication;

import java.util.Collection;

public interface PublicationRepository extends TisRepository<Publication, Long> {
    Collection<Publication> getByAdviserId(Long adviserId, Publication.PublicationType type);

    Collection<Publication> getByAdviserIdSemester(Long adviserId, Publication.PublicationType type, String semester);
}
