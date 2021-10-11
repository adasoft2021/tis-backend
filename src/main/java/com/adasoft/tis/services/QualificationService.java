package com.adasoft.tis.services;

import com.adasoft.tis.core.exceptions.DefaultTisDomainException;
import com.adasoft.tis.core.exceptions.EntityNotFoundException;
import com.adasoft.tis.domain.BaseQualification;
import com.adasoft.tis.domain.Qualification;
import com.adasoft.tis.domain.Review;
import com.adasoft.tis.dto.qualification.QualificationResponseDTO;
import com.adasoft.tis.dto.qualification.UpdateQualificationDTO;
import com.adasoft.tis.repository.BaseQualificationRepository;
import com.adasoft.tis.repository.QualificationRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class QualificationService {
    private QualificationRepository qualificationRepository;
    private BaseQualificationRepository baseQualificationRepository;
    private ModelMapper qualificationMapper;

    private Qualification validateScore(
            final Qualification qualification,
            final UpdateQualificationDTO qualificationDTO) {
        if (qualificationDTO.getScore() != null) {
            int scoreValid = 0;
            if (qualificationDTO.getScore() < scoreValid) {
                throw new DefaultTisDomainException(
                        HttpStatus.BAD_REQUEST,
                        String.format("[qualificationId: %d], [score Este campo no puede ser menor a %d]",
                                qualification.getId(), scoreValid));
            }

            scoreValid = qualification.getBaseQualification().getMaxScore();
            if (qualificationDTO.getScore() > scoreValid) {
                throw new DefaultTisDomainException(
                        HttpStatus.BAD_REQUEST,
                        String.format("[qualificationId: %d], [score Este campo no puede ser mayor a %d]",
                                qualification.getId(), scoreValid));
            }
        }

        qualificationMapper.map(qualificationDTO, qualification);
        return qualification;
    }

    private Collection<Qualification> getQualificationsByIdToUpdate(
            final Review review,
            final Collection<UpdateQualificationDTO> qualificationDTOS) {
        return qualificationDTOS.stream().map((qualificationDTO -> {
            Qualification qualification = qualificationRepository.findById(qualificationDTO.getQualificationId())
                    .orElseThrow(
                            () -> new EntityNotFoundException(Qualification.class, qualificationDTO.getQualificationId()));

            if (!qualification.getReview().equals(review)) {
                throw new DefaultTisDomainException(
                        HttpStatus.NOT_ACCEPTABLE, "No está permitido editar la entidad Qualification.");
            }

            return validateScore(qualification, qualificationDTO);
        })).collect(Collectors.toSet());
    }

    public Collection<QualificationResponseDTO> createAll(final Review review) {
        Collection<BaseQualification> baseQualifications = baseQualificationRepository.getAll();

        return baseQualifications.stream().map((baseQualification -> {
            Qualification qualification = Qualification.builder()
                    .createdAt(review.getCreatedAt())
                    .updatedAt(review.getUpdatedAt())
                    .review(review)
                    .baseQualification(baseQualification)
                    .build();

            qualification = qualificationRepository.save(qualification);
            return qualificationMapper.map(qualification, QualificationResponseDTO.class);
        })).collect(Collectors.toSet());
    }

    public Collection<QualificationResponseDTO> updateAll(
            final Review review,
            final Collection<UpdateQualificationDTO> qualificationDTOS) {
        Collection<Qualification> qualifications = getQualificationsByIdToUpdate(review, qualificationDTOS);

        qualifications = qualificationRepository.updateAll(qualifications);

        return qualifications.stream()
                .map((qualification -> qualificationMapper.map(qualification, QualificationResponseDTO.class)))
                .collect(Collectors.toSet());
    }
}
