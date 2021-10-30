package com.adasoft.tis.services;

import com.adasoft.tis.core.exceptions.DefaultTisDomainException;
import com.adasoft.tis.core.exceptions.EntityNotFoundException;
import com.adasoft.tis.core.utils.CodeGenerator;
import com.adasoft.tis.domain.Adviser;
import com.adasoft.tis.domain.ClassCode;
import com.adasoft.tis.dto.classCode.ClassCodeResponseDTO;
import com.adasoft.tis.repository.AdviserRepository;
import com.adasoft.tis.repository.ClassCodeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.adasoft.tis.core.utils.Preconditions.checkArgument;

@AllArgsConstructor
@Service
public class ClassCodeService {
    private ClassCodeRepository classCodeRepository;
    private ModelMapper classCodeMapper;
    private AdviserRepository adviserRepository;
    @Autowired
    private CodeGenerator codeGenerator;

    public ClassCodeResponseDTO create(final Long adviserId) {
        checkArgument(adviserId != null, "El id de Adviser no puede ser nulo.");

        Adviser foundAdviser = adviserRepository.findById(adviserId)
            .orElseThrow(() -> new EntityNotFoundException(Adviser.class, adviserId));

        if (foundAdviser.isDeleted()) {
            throw new EntityNotFoundException(Adviser.class, adviserId);
        }
        String code;

        long max = 5429503678976L;
        long i = 0L;
        do {
            code = codeGenerator.generateLetterCode(9, 3, '-');
            i++;
        } while (classCodeRepository.existByCode(code) && i < max);
        if (i == max)
            throw new DefaultTisDomainException(HttpStatus.CONFLICT, "Se ha excedido la cantidad de ClassCode");


        ClassCode classCode = new ClassCode();
        classCode.setCode(code);
        classCode.setCreatedBy(foundAdviser);
        classCode.setCreatedAt(LocalDateTime.now());
        classCode.setDeleted(false);
        classCode.setUpdatedAt(LocalDateTime.now());


        ClassCode persistedClassCode = classCodeRepository.save(classCode);

        return classCodeMapper.map(persistedClassCode, ClassCodeResponseDTO.class);
    }

    public ClassCodeResponseDTO getByAdviserId(final Long adviserId) {
        checkArgument(adviserId != null, "El id de Adviser no puede ser nulo.");

        Adviser foundAdviser = adviserRepository.findById(adviserId)
            .orElseThrow(() -> new EntityNotFoundException(Adviser.class, adviserId));

        if (foundAdviser.isDeleted()) {
            throw new EntityNotFoundException(Adviser.class, adviserId);
        }
        ClassCode foundClassCode = classCodeRepository.findByAdviserId(adviserId)
            .orElseThrow(() -> new EntityNotFoundException(ClassCode.class, adviserId) {
                @Override
                protected String getExceptionDetail() {
                    return super.getExceptionDetail().replaceAll("id", "adviserId");
                }
            });

        return classCodeMapper.map(foundClassCode, ClassCodeResponseDTO.class);
    }
}
