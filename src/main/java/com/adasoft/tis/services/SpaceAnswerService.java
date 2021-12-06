package com.adasoft.tis.services;

import com.adasoft.tis.core.exceptions.EntityNotFoundException;
import com.adasoft.tis.domain.*;
import com.adasoft.tis.dto.spaceAnswer.CompanySpaceAnswersResponseDTO;
import com.adasoft.tis.dto.spaceAnswer.CreateSpaceAnswerDTO;
import com.adasoft.tis.dto.spaceAnswer.SemesterSpaceAnswersResponseDTO;
import com.adasoft.tis.dto.spaceAnswer.SpaceAnswerResponseDTO;
import com.adasoft.tis.repository.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.adasoft.tis.core.utils.Preconditions.checkArgument;

@AllArgsConstructor
@Service
public class SpaceAnswerService {
    private SpaceAnswerRepository spaceAnswerRepository;
    private SpaceRepository spaceRepository;
    private CompanyRepository companyRepository;
    private ModelMapper spaceAnswerMapper;
    private AdviserRepository adviserRepository;
    private SemesterRepository semesterRepository;
    private ModelMapper semesterMapper;

    public SpaceAnswerResponseDTO create(final Long spaceId, final CreateSpaceAnswerDTO spaceDTO) {
        checkArgument(spaceId != null, "El spaceId no puede ser nulo.");
        checkArgument(Objects.equals(spaceId, spaceDTO.getSpaceId()), "Los ids de Space no coinciden");
        checkArgument(spaceDTO != null, "El SpaceAnswerDTO  no puede ser nulo.");

        Space foundSpace = spaceRepository.findById(spaceDTO.getSpaceId())
            .orElseThrow(() -> new EntityNotFoundException(Space.class, spaceDTO.getSpaceId()));

        Company foundCompany = companyRepository.findById(spaceDTO.getCreatedById())
            .orElseThrow(() -> new EntityNotFoundException(Company.class, spaceDTO.getCreatedById()));

        SpaceAnswer defaultSpaceAnswer = spaceAnswerMapper.map(spaceDTO, SpaceAnswer.class);
        defaultSpaceAnswer.setSpace(foundSpace);
        defaultSpaceAnswer.setCreatedBy(foundCompany);

        SpaceAnswer persistedSpaceAnswer = spaceAnswerRepository.save(defaultSpaceAnswer);

        return spaceAnswerMapper.map(persistedSpaceAnswer, SpaceAnswerResponseDTO.class);
    }


    public Collection<SpaceAnswerResponseDTO> getBySpaceId(final Long adviserId, final Long spaceId) {
        checkArgument(adviserId != null, "El id de Adviser no puede ser nulo.");
        checkArgument(spaceId != null, "El id de Space no puede ser nulo.");
        if (!adviserRepository.exists(adviserId))
            throw new EntityNotFoundException(Adviser.class, adviserId);
        spaceRepository.findById(spaceId)
            .orElseThrow(() -> new EntityNotFoundException(Space.class, spaceId));

        Collection<SpaceAnswer> spaceAnswers = spaceAnswerRepository.getBySpaceId(spaceId);

        return spaceAnswers.stream().filter(spaceAnswer -> !spaceAnswer.isDeleted())
            .map(space -> spaceAnswerMapper.map(space, SpaceAnswerResponseDTO.class))
            .collect(Collectors.toSet());
    }

    public Collection<SpaceAnswerResponseDTO> getBySpaceIdAndCompanyId(final Long spaceId, final Long companyId) {
        checkArgument(spaceId != null, "El id de Space no puede ser nulo.");
        spaceRepository.findById(spaceId)
            .orElseThrow(() -> new EntityNotFoundException(Space.class, spaceId));
        companyRepository.findById(companyId)
            .orElseThrow(() -> new EntityNotFoundException(Company.class, companyId));
        Collection<SpaceAnswer> spaceAnswers = spaceAnswerRepository.getBySpaceId(spaceId);

        return spaceAnswers.stream().filter(spaceAnswer -> !spaceAnswer.isDeleted())
            .filter(spaceAnswer -> spaceAnswer.getCreatedBy().getId().equals(companyId))
            .map(spaceAnswer -> spaceAnswerMapper.map(spaceAnswer, SpaceAnswerResponseDTO.class))
            .collect(Collectors.toSet());
    }

    public Collection<SemesterSpaceAnswersResponseDTO> getAdviserHistory(final Long adviserId) {
        checkArgument(adviserId != null, "El ID de Adviser no puede ser nulo.");
        LinkedList<SemesterSpaceAnswersResponseDTO> dtos = new LinkedList<>();
        List<Semester> semesters = semesterRepository.getAll();
        for (Semester s : semesters) {
            semesterMapper.map(s, SemesterSpaceAnswersResponseDTO.class);
            Collection<Company> companies = companyRepository.getSemesterCompanies(s.getSemester(), adviserId);
            Collection<CompanySpaceAnswersResponseDTO> companiesAnswers = new HashSet<>();
            for (Company c : companies) {
                HashSet<SpaceAnswer> companyAnswers = new HashSet<>();
                for (CompanySpace cs : c.getAssigned()) {
                    companyAnswers.addAll(spaceAnswerRepository.findCompanyAnswers(c.getId(), cs.getSpace().getId()));
                }
                if (!companyAnswers.isEmpty()) {
                    List<FileEntity> all = companyAnswers.stream().map(SpaceAnswer::getFiles).reduce((f, l) ->
                    {
                        List<FileEntity> files = new LinkedList<>();
                        files.addAll(f);
                        files.addAll(l);
                        return files;
                    }).orElse(null);
                    Iterator<SpaceAnswer> i = companyAnswers.iterator();
                    SpaceAnswer ans = i.next();
                    ans.setFiles(all);
                    companiesAnswers.add(spaceAnswerMapper.map(ans, CompanySpaceAnswersResponseDTO.class));
                }

            }
        }
        return dtos;
    }
}
