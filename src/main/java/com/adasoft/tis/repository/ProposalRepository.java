package com.adasoft.tis.repository;

import com.adasoft.tis.core.repository.TisRepository;
import com.adasoft.tis.domain.BaseQualification;
import com.adasoft.tis.domain.Proposal;
import com.adasoft.tis.dto.proposal.ProposalResponseDTO;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ProposalRepository extends TisRepository<Proposal, Long> {
    List<Proposal> getAllByAdviserId(Long adviserId);
}
