package com.adasoft.tis.services;

import com.adasoft.tis.core.exceptions.EntityNotFoundException;
import com.adasoft.tis.domain.Company;
import com.adasoft.tis.dto.company.CompanyResponseDTO;
import com.adasoft.tis.dto.company.CreateCompanyDTO;
import com.adasoft.tis.dto.company.UpdateCompanyDTO;
import com.adasoft.tis.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
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

    public CompanyResponseDTO create(final CreateCompanyDTO companyDTO) {
        checkArgument(companyDTO != null, "El CompanyDTO a crear no puede ser nulo.");

        Company defaultCompany = companyMapper.map(companyDTO, Company.class);

        Company persistedCompany = companyRepository.save(defaultCompany);

        return companyMapper.map(persistedCompany, CompanyResponseDTO.class);
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
        Collection<CompanyResponseDTO> companys = companyRepository.getAll()
            .stream().filter(company -> !company.isDeleted())
            .map(company -> companyMapper.map(company, CompanyResponseDTO.class))
            .collect(Collectors.toSet());
        return new HashSet<>(companys);
    }
}
