package com.adasoft.tis.services;

import com.adasoft.tis.core.exceptions.EntityNotFoundException;
import com.adasoft.tis.domain.Adviser;
import com.adasoft.tis.domain.Company;
import com.adasoft.tis.domain.Space;
import com.adasoft.tis.dto.space.SpaceCompactResponseDTO;
import com.adasoft.tis.dto.space.SpaceResponseDTO;
import com.adasoft.tis.repository.AdviserRepository;
import com.adasoft.tis.repository.CompanyRepository;
import com.adasoft.tis.repository.SpaceRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class SpaceService {
    private AdviserRepository adviserRepository;
    private SpaceRepository spaceRepository;
    private ModelMapper spaceMapper;
    private CompanyRepository companyRepository;

    public Collection<SpaceCompactResponseDTO> getAdviserSpaces(Long adviserId, Space.SpaceType spaceType) {
        adviserRepository.findById(adviserId).orElseThrow(() -> new EntityNotFoundException(Adviser.class, adviserId));

        Collection<Space> spaces = spaceRepository.findByAdviser(adviserId, spaceType);
        return spaces.stream().map(space -> spaceMapper.map(space, SpaceCompactResponseDTO.class))
            .collect(Collectors.toSet());
    }

    public Collection<SpaceCompactResponseDTO> getCompanySpaces(Long companyId, Space.SpaceType spaceType) {
        Company foundCompany = companyRepository.findById(companyId)
            .orElseThrow(() -> new EntityNotFoundException(Company.class, companyId));
        Collection<Space> spaces = spaceRepository.findByAdviser(foundCompany.getAdviser().getId(), spaceType);
        if (foundCompany.getProject() != null)
            spaces = spaces.stream().filter(space ->
                    space.getProject().getId().equals(foundCompany.getProject().getId()))
                .collect(Collectors.toSet());
        return spaces.stream().map(space -> spaceMapper.map(space, SpaceCompactResponseDTO.class))
            .collect(Collectors.toSet());
    }

    public SpaceResponseDTO getSpace(Long spaceId) {
        Space space = spaceRepository.findById(spaceId)
            .orElseThrow(() -> new EntityNotFoundException(Space.class, spaceId));
        return spaceMapper.map(space, SpaceResponseDTO.class);
    }

}
