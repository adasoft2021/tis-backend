package com.adasoft.tis.services;

import com.adasoft.tis.core.exceptions.EntityNotFoundException;
import com.adasoft.tis.domain.Qualification;
import com.adasoft.tis.domain.Review;
import com.adasoft.tis.dto.qualification.CreateQualificationDTO;
import com.adasoft.tis.dto.qualification.QualificationResponseDTO;
import com.adasoft.tis.dto.qualification.UpdateQualificationDTO;
import com.adasoft.tis.repository.QualificationRepository;
import com.adasoft.tis.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import static com.adasoft.tis.core.utils.Preconditions.checkArgument;

@AllArgsConstructor
@Service
public class QualificationService {
    private QualificationRepository qualificationRepository;
    private ReviewRepository reviewRepository;
    private ModelMapper qualificationMapper;

    public QualificationResponseDTO create(final Long reviewId, final CreateQualificationDTO qualificationDTO) {
        checkArgument(reviewId != null, "El id de Review no puede ser nulo.");
        checkArgument(qualificationDTO != null, "El QualificationDTO a crear no puede ser nulo.");

        Review foundReview = reviewRepository.findById(reviewId)
            .orElseThrow(() -> new EntityNotFoundException(Review.class, reviewId));

        Qualification defaultQualification = qualificationMapper.map(qualificationDTO, Qualification.class);
        defaultQualification.setReview(foundReview);

        Qualification persistedQualification = qualificationRepository.save(defaultQualification);

        return qualificationMapper.map(persistedQualification, QualificationResponseDTO.class);
    }

    public QualificationResponseDTO update(final Long qualificationId, final UpdateQualificationDTO qualificationDTO) {
        checkArgument(
            qualificationId != null,
            "El id de Qualification a actualizar no puede ser nulo.");
        checkArgument(qualificationDTO != null, "El QualificationDTO a crear no puede ser nulo.");

        Qualification foundQualification = qualificationRepository.findById(qualificationId)
            .orElseThrow(() -> new EntityNotFoundException(Qualification.class, qualificationId));

        qualificationMapper.map(qualificationDTO, foundQualification);
        foundQualification = qualificationRepository.update(foundQualification);

        return qualificationMapper.map(foundQualification, QualificationResponseDTO.class);
    }
}
