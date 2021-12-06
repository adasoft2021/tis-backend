package com.adasoft.tis.services;

import com.adasoft.tis.core.exceptions.DefaultTisDomainException;
import com.adasoft.tis.core.exceptions.EntityNotFoundException;
import com.adasoft.tis.core.utils.JWTProvider;
import com.adasoft.tis.domain.Adviser;
import com.adasoft.tis.domain.ClassCode;
import com.adasoft.tis.domain.Company;
import com.adasoft.tis.dto.company.CompanyResponseDTO;
import com.adasoft.tis.dto.company.CreateCompanyDTO;
import com.adasoft.tis.dto.company.UpdateCompanyDTO;
import com.adasoft.tis.dto.user.UserResponseDTO;
import com.adasoft.tis.repository.AdviserRepository;
import com.adasoft.tis.repository.ClassCodeRepository;
import com.adasoft.tis.repository.CompanyRepository;
import com.adasoft.tis.repository.SemesterRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

import static com.adasoft.tis.core.utils.Preconditions.checkArgument;

@AllArgsConstructor
@Service
public class CompanyService {
    private CompanyRepository companyRepository;
    private ModelMapper companyMapper;
    private ClassCodeRepository classCodeRepository;
    private JWTProvider jwtProvider;
    private AdviserRepository adviserRepository;
    private SemesterRepository semesterRepository;

    public UserResponseDTO create(final String registrationCode, final CreateCompanyDTO companyDTO) {
        checkArgument(registrationCode != null, "El Codigo de Registro no puede ser nulo.");
        checkArgument(companyDTO != null, "El CompanyDTO a crear no puede ser nulo.");

        if (!classCodeRepository.existByCode(registrationCode)) {
            throw new EntityNotFoundException(ClassCode.class, registrationCode);
        }

        ClassCode foundCode = classCodeRepository.getByCode(registrationCode)
            .orElseThrow(() -> new DefaultTisDomainException(HttpStatus.SERVICE_UNAVAILABLE, "Internal Server Error"));
        if (companyRepository.existName(companyDTO.getName())) {
            throw new DefaultTisDomainException(HttpStatus.CONFLICT, "Ya existe la grupo empresa " + companyDTO.getName() + " en el sistema.");
        }
        Company defaultCompany = companyMapper.map(companyDTO, Company.class);
        defaultCompany.setAdviser(foundCode.getCreatedBy());

        Company persistedCompany = companyRepository.save(defaultCompany);

        UserResponseDTO responseDTO = companyMapper.map(persistedCompany, UserResponseDTO.class);
        responseDTO.setToken(jwtProvider.create(persistedCompany.getId()));
        Class<?> companyClass = Company.class;
        responseDTO.setUserType(companyClass.getSimpleName().toUpperCase());
        return responseDTO;
    }

    public CompanyResponseDTO update(final Long companyId, final UpdateCompanyDTO companyDTO) {
        checkArgument(companyId != null, "El id de Company a actualizar no puede ser nulo.");
        checkArgument(companyDTO != null, "El CompanyDTO a actualizar no puede ser nulo.");

        Company foundCompany = companyRepository.findById(companyId)
            .orElseThrow(() -> new EntityNotFoundException(Company.class, companyId));

        if (foundCompany.isDeleted()) {
            throw new EntityNotFoundException(Company.class, companyId);
        }

        companyMapper.map(companyDTO, foundCompany);
        companyRepository.update(foundCompany);

        return companyMapper.map(foundCompany, CompanyResponseDTO.class);
    }

    public CompanyResponseDTO delete(final Long companyId) {
        checkArgument(companyId != null, "El id de Company a actualizar no puede ser nulo.");

        Company foundCompany = companyRepository.findById(companyId)
            .orElseThrow(() -> new EntityNotFoundException(Company.class, companyId));

        if (foundCompany.isDeleted()) {
            throw new EntityNotFoundException(Company.class, companyId);
        }
        companyRepository.deleteById(companyId);
        foundCompany.setDeleted(true);
        return companyMapper.map(foundCompany, CompanyResponseDTO.class);
    }

    public CompanyResponseDTO getById(final Long companyId) {
        checkArgument(companyId != null, "El id de Company a obtener no puede ser nulo.");

        Company foundCompany = companyRepository.findById(companyId)
            .orElseThrow(() -> new EntityNotFoundException(Company.class, companyId));

        if (foundCompany.isDeleted()) {
            throw new EntityNotFoundException(Company.class, companyId);
        }

        return companyMapper.map(foundCompany, CompanyResponseDTO.class);
    }

    public Collection<CompanyResponseDTO> getAll() {
        return companyRepository.getAll()
            .stream().filter(company -> !company.isDeleted())
            .map(company -> companyMapper.map(company, CompanyResponseDTO.class)).collect(Collectors.toSet());
    }

    public Collection<CompanyResponseDTO> getSemesterCompanies(String semester, Long adviserId) {
        adviserRepository.findById(adviserId)
            .orElseThrow(() -> new EntityNotFoundException(Adviser.class, adviserId));

        semesterRepository.findBySemester(semester)
            .orElseThrow(() -> new DefaultTisDomainException(HttpStatus.NOT_FOUND,
                "No existe informacion del semestre " + semester));
        
        return companyRepository.getSemesterCompanies(semester, adviserId)
            .stream().filter(company -> !company.isDeleted())
            .map(company -> companyMapper.map(company, CompanyResponseDTO.class)).collect(Collectors.toSet());

    }

    public Collection<Collection<CompanyResponseDTO>> groupByNumberOfPartners(Long adviserId, String semester) {
        checkArgument(adviserId != null, "El id de Adviser a obtener no puede ser nulo.");
        checkArgument(semester != null, "semester no puede ser nulo.");

        adviserRepository.findById(adviserId)
            .orElseThrow(() -> new EntityNotFoundException(Adviser.class, adviserId));

        semesterRepository.findBySemester(semester)
            .orElseThrow(() -> new DefaultTisDomainException(HttpStatus.NOT_FOUND,
                "No existe informacion del semestre " + semester));

        Collection<Collection<CompanyResponseDTO>> groupedCompanies = new HashSet<>();
        for (int i = 0; i < 6; i++) {
            groupedCompanies.add(companyRepository.getSemesterCompanies(semester, adviserId, i)
                .stream().filter(company -> !company.isDeleted())
                .map(company -> companyMapper.map(company, CompanyResponseDTO.class))
                .collect(Collectors.toSet()));
        }
        return groupedCompanies;
    }

}
