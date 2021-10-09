package com.adasoft.tis.repository;

import com.adasoft.tis.core.repository.TisRepository;
import com.adasoft.tis.domain.Proposal;

import java.util.List;

public interface ProposalRepository extends TisRepository<Proposal, Long> {
    List<Proposal> getAllByAdviserId(Long adviserId);
}
