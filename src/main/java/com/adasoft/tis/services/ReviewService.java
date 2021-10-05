package com.adasoft.tis.services;

import com.adasoft.tis.core.exceptions.EntityNotFoundException;
import com.adasoft.tis.domain.Review;
import com.adasoft.tis.dto.review.CreateReviewDTO;
import com.adasoft.tis.dto.review.ReviewResponseDTO;
import com.adasoft.tis.dto.review.UpdateReviewDTO;
import com.adasoft.tis.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import static com.adasoft.tis.core.utils.Preconditions.checkArgument;

@AllArgsConstructor
@Service
public class ReviewService {
    private ReviewRepository reviewRepository;
    private ModelMapper reviewMapper;

    public ReviewResponseDTO create(final CreateReviewDTO reviewDTO) {
        checkArgument(reviewDTO != null, "El ReviewDTO a crear no puede ser nulo.");

        Review defaultReview = reviewMapper.map(reviewDTO, Review.class);

        Review persistedReview = reviewRepository.save(defaultReview);

        return reviewMapper.map(persistedReview, ReviewResponseDTO.class);
    }

    public ReviewResponseDTO update(final Long reviewId, final UpdateReviewDTO reviewDTO) {
        checkArgument(reviewId != null, "El id de Review a actualizar no puede ser nulo.");
        checkArgument(reviewDTO != null, "El ReviewDTO a actualizar no puede ser nulo.");

        Review foundReview = reviewRepository.findById(reviewId)
            .orElseThrow(() -> new EntityNotFoundException(Review.class, reviewId));

        if (foundReview.isDeleted()) {
            throw new EntityNotFoundException(Review.class, reviewId);
        }

        reviewMapper.map(reviewDTO, foundReview);

        return reviewMapper.map(foundReview, ReviewResponseDTO.class);
    }
}
