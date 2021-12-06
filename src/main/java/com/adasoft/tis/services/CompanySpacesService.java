package com.adasoft.tis.services;

import com.adasoft.tis.core.exceptions.EntityNotFoundException;
import com.adasoft.tis.domain.Adviser;
import com.adasoft.tis.domain.Company;
import com.adasoft.tis.domain.Review;
import com.adasoft.tis.domain.Space;
import com.adasoft.tis.dto.review.ReviewCompactResponseDTO;
import com.adasoft.tis.dto.space.CompanySpaceAndAnswersResponseDTO;
import com.adasoft.tis.dto.space.CompanySpacesResponseDTO;
import com.adasoft.tis.dto.space.SpaceResponseDTO;
import com.adasoft.tis.dto.spaceAnswer.SpaceAnswerResponseDTO;
import com.adasoft.tis.repository.AdviserRepository;
import com.adasoft.tis.repository.CompanyRepository;
import com.adasoft.tis.repository.CompanySpaceRepository;
import com.adasoft.tis.repository.SpaceAnswerRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CompanySpacesService {
    private AdviserRepository adviserRepository;
    private CompanyRepository companyRepository;
    private CompanySpaceRepository companySpaceRepository;
    private ModelMapper reviewMapper;
    private SpaceAnswerRepository spaceAnswerRepository;
    private ModelMapper spaceMapper;
    private ModelMapper spaceAnswerMapper;

    public Collection<CompanySpacesResponseDTO> getAdviserSpacesAndAnswers(Long adviserId, Long projectId) {
        adviserRepository.findById(adviserId).orElseThrow(() -> new EntityNotFoundException(Adviser.class, adviserId));
        Collection<CompanySpacesResponseDTO> companySpacesDTOs = new HashSet<>();
        LinkedList<Company> companies = (LinkedList<Company>) companyRepository.findByProject(projectId);

        for (Company c : companies) {
            CompanySpacesResponseDTO dto = new CompanySpacesResponseDTO();
            companySpacesDTOs.add(dto);
            dto.setCompanyName(c.getName());
            Collection<Object[]> becauseOfs = companySpaceRepository.getCountCompanySpacesByReview(c.getId());
            dto.setAnswered(new LinkedList<>());
            dto.setUnanswered(new LinkedList<>());
            for (Object[] o : becauseOfs) {
                CompanySpaceAndAnswersResponseDTO subDtoAnswered = new CompanySpaceAndAnswersResponseDTO();
                CompanySpaceAndAnswersResponseDTO subDtoUnanswered = new CompanySpaceAndAnswersResponseDTO();
                if (o[0] != null) {
                    subDtoAnswered.setBecauseOf(reviewMapper.map(o[0], ReviewCompactResponseDTO.class));
                    subDtoUnanswered.setBecauseOf(reviewMapper.map(o[0], ReviewCompactResponseDTO.class));
                }
                LinkedList<Space> spacesAnswered =
                    (LinkedList<Space>) companySpaceRepository.getSpacesByReview((Review) o[0], true);
                LinkedList<Space> spacesUnanswered =
                    (LinkedList<Space>) companySpaceRepository.getSpacesByReview((Review) o[0], false);
                dto.getAnswered().add(getSpaceAndAnswers(spacesAnswered, subDtoAnswered));
                dto.getUnanswered().add(getSpaceAndAnswers(spacesUnanswered, subDtoUnanswered));
            }
        }
        return companySpacesDTOs;
    }

    private CompanySpaceAndAnswersResponseDTO getSpaceAndAnswers(
        LinkedList<Space> spaces, CompanySpaceAndAnswersResponseDTO subDto) {
        subDto.setSpaces(spaces.stream()
            .map(space -> spaceMapper.map(space, SpaceResponseDTO.class))
            .collect(Collectors.toSet()));
        subDto.setSpaceAnswers(new HashSet<>());
        for (Space s : spaces) {
            subDto.getSpaceAnswers().add(
                spaceAnswerRepository.getBySpaceId(s.getId()).stream()
                    .map(a -> spaceAnswerMapper.map(a, SpaceAnswerResponseDTO.class))
                    .collect(Collectors.toSet()));
        }
        return subDto;
    }
}
