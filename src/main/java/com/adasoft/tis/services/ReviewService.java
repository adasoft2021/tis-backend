package com.adasoft.tis.services;

import com.adasoft.tis.core.exceptions.DefaultTisDomainException;
import com.adasoft.tis.core.exceptions.EntityNotFoundException;
import com.adasoft.tis.domain.Review;
import com.adasoft.tis.dto.qualification.QualificationResponseDTO;
import com.adasoft.tis.dto.qualification.UpdateQualificationDTO;
import com.adasoft.tis.dto.review.CreateReviewDTO;
import com.adasoft.tis.dto.review.ReviewResponseDTO;
import com.adasoft.tis.dto.review.UpdateReviewDTO;
import com.adasoft.tis.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;

import static com.adasoft.tis.core.utils.Preconditions.checkArgument;

@AllArgsConstructor
@Service
public class ReviewService {
    private ReviewRepository reviewRepository;
    private ModelMapper reviewMapper;

    private QualificationService qualificationService;

    private Collection<QualificationResponseDTO> updateQualifications(
        final Review review,
        final Collection<UpdateQualificationDTO> qualificationDTOS) {
        if (review.getTotalScore() != null) {
            throw new DefaultTisDomainException(
                HttpStatus.METHOD_NOT_ALLOWED,
                "Usted ya no puede hacer ningún cambio en la entidad Review.");
        }

        return qualificationService.updateAll(review, qualificationDTOS);
    }

    private ReviewResponseDTO updateReview(
        final Review review,
        final Collection<UpdateQualificationDTO> qualificationDTOS) {
        Collection<QualificationResponseDTO> qualificationResponseDTOS = updateQualifications(review, qualificationDTOS);

        boolean fullNotes = true;
        int totalScore = 0;
        for (QualificationResponseDTO qualificationResponseDTO : qualificationResponseDTOS) {
            if (qualificationResponseDTO.getScore() != null) {
                totalScore += qualificationResponseDTO.getScore();
                continue;
            }
            fullNotes = false;
            break;
        }
        if (fullNotes) {
            review.setTotalScore(totalScore);
        }
        reviewRepository.update(review);

        ReviewResponseDTO responseDTO = reviewMapper.map(review, ReviewResponseDTO.class);
        responseDTO.setQualifications(new HashSet<>(qualificationResponseDTOS));

        return responseDTO;
    }

    public ReviewResponseDTO create(final CreateReviewDTO reviewDTO) {
        checkArgument(reviewDTO != null, "El ReviewDTO a crear no puede ser nulo.");

        Review defaultReview = reviewMapper.map(reviewDTO, Review.class);

        Review persistedReview = reviewRepository.save(defaultReview);

        Collection<QualificationResponseDTO> qualificationResponseDTOS = qualificationService.createAll(persistedReview);

        ReviewResponseDTO responseDTO = reviewMapper.map(persistedReview, ReviewResponseDTO.class);
        responseDTO.setQualifications(new HashSet<>(qualificationResponseDTOS));

        return responseDTO;
    }

    public ReviewResponseDTO update(final Long reviewId, final UpdateReviewDTO reviewDTO) {
        checkArgument(reviewId != null, "El id de Review a actualizar no puede ser nulo.");
        checkArgument(reviewDTO != null, "El ReviewDTO a actualizar no puede ser nulo.");

        Review foundReview = reviewRepository.findById(reviewId)
            .orElseThrow(() -> new EntityNotFoundException(Review.class, reviewId));

        reviewMapper.map(reviewDTO, foundReview);
        return updateReview(foundReview, reviewDTO.getQualifications());
    }
}
