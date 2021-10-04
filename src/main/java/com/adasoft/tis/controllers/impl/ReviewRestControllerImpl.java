package com.adasoft.tis.controllers.impl;

import com.adasoft.tis.controllers.ReviewRestController;
import com.adasoft.tis.dto.review.CreateReviewDTO;
import com.adasoft.tis.dto.review.ReviewResponseDTO;
import com.adasoft.tis.dto.review.UpdateReviewDTO;
import com.adasoft.tis.services.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/reviews")
@AllArgsConstructor
public class ReviewRestControllerImpl implements ReviewRestController {
    private ReviewService reviewService;

    @PostMapping
    @Override
    public ResponseEntity<ReviewResponseDTO> create(@Valid @RequestBody final CreateReviewDTO reviewDTO) {
        ReviewResponseDTO responseDTO = reviewService.create(reviewDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{reviewId}")
    @Override
    public ResponseEntity<ReviewResponseDTO> update(
        @NotNull final Long id,
        @Valid @RequestBody final UpdateReviewDTO reviewDTO) {
        ReviewResponseDTO responseDTO = reviewService.update(id, reviewDTO);
        return ResponseEntity.ok(responseDTO);
    }
}
