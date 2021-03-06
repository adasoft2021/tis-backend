package com.adasoft.tis.services;

import com.adasoft.tis.core.exceptions.EntityNotFoundException;
import com.adasoft.tis.core.utils.CodeGenerator;
import com.adasoft.tis.domain.Adviser;
import com.adasoft.tis.domain.ClassCode;
import com.adasoft.tis.dto.classCode.ClassCodeResponseDTO;
import com.adasoft.tis.dto.classCode.CreateClassCodeDTO;
import com.adasoft.tis.repository.AdviserRepository;
import com.adasoft.tis.repository.ClassCodeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

        do {
            code = codeGenerator.generateLetterCode(9, 3, '-');
        } while (classCodeRepository.existByCode(code));

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

    public Object validateClassCode(final CreateClassCodeDTO classCodeDTO) {
        checkArgument(classCodeDTO != null, "El CreateClassCodeDTO no puede ser nulo.");
        boolean classCodeExists = classCodeRepository.existByCode(classCodeDTO.getCode());
        if (!classCodeExists) {
            throw new EntityNotFoundException(ClassCode.class, 1) {
                @Override
                protected String getExceptionDetail() {
                    String message = super.getExceptionDetail().replace("id", "c??digo de clase");
                    return message.replace("1", classCodeDTO.getCode());
                }
            };
        }
        return null;
    }
}
