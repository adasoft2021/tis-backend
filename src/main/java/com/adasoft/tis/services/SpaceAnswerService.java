package com.adasoft.tis.services;

import com.adasoft.tis.core.exceptions.EntityNotFoundException;
import com.adasoft.tis.domain.Company;
import com.adasoft.tis.domain.Space;
import com.adasoft.tis.domain.SpaceAnswer;
import com.adasoft.tis.dto.spaceAnswer.CreateSpaceAnswerDTO;
import com.adasoft.tis.dto.spaceAnswer.SpaceAnswerResponseDTO;
import com.adasoft.tis.repository.CompanyRepository;
import com.adasoft.tis.repository.SpaceAnswerRepository;
import com.adasoft.tis.repository.SpaceRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

import static com.adasoft.tis.core.utils.Preconditions.checkArgument;

@AllArgsConstructor
@Service
public class SpaceAnswerService {
    private SpaceAnswerRepository spaceAnswerRepository;
    private SpaceRepository spaceRepository;
    private CompanyRepository companyRepository;
    private ModelMapper spaceAnswerMapper;

    public SpaceAnswerResponseDTO create(final CreateSpaceAnswerDTO spaceDTO) {
        checkArgument(spaceDTO != null, "El SpaceAnswerDTO a actualizar no puede ser nulo.");

        Space foundSpace = spaceRepository.findById(spaceDTO.getSpaceId())
            .orElseThrow(() -> new EntityNotFoundException(Space.class, spaceDTO.getCreatedById()));

        Company foundCompany = companyRepository.findById(spaceDTO.getCreatedById())
            .orElseThrow(() -> new EntityNotFoundException(Space.class, spaceDTO.getCreatedById()));

        SpaceAnswer defaultSpaceAnswer = spaceAnswerMapper.map(spaceDTO, SpaceAnswer.class);
        defaultSpaceAnswer.setSpace(foundSpace);
        defaultSpaceAnswer.setCreatedBy(foundCompany);

        SpaceAnswer persistedSpaceAnswer = spaceAnswerRepository.save(defaultSpaceAnswer);

        return spaceAnswerMapper.map(persistedSpaceAnswer, SpaceAnswerResponseDTO.class);
    }


    public Collection<SpaceAnswerResponseDTO> getBySpaceId(final Long spaceId) {
        checkArgument(spaceId != null, "El id de Space no puede ser nulo.");

        spaceRepository.findById(spaceId)
            .orElseThrow(() -> new EntityNotFoundException(Space.class, spaceId));

        Collection<SpaceAnswer> spaceAnswers = spaceAnswerRepository.getBySpaceId(spaceId);

        return spaceAnswers.stream()
            .map(space -> spaceAnswerMapper.map(space, SpaceAnswerResponseDTO.class))
            .collect(Collectors.toSet());
    }
}
