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

import static com.adasoft.tis.core.utils.Preconditions.checkUserId;

@RestController
@RequestMapping("/reviews")
@AllArgsConstructor
public class ReviewRestControllerImpl implements ReviewRestController {
    private ReviewService reviewService;
    private Long userId;
    private Long id;

    @GetMapping("/{reviewId}")
    @Override
    public ResponseEntity<ReviewResponseDTO> get(
        @RequestAttribute("userId") final Long userId,
        @NotNull @PathVariable("reviewId") Long id) {
        ReviewResponseDTO responseDTO = reviewService.get(id);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping
    @Override
    public ResponseEntity<ReviewResponseDTO> create(
        @RequestAttribute("userId") final Long userId,
        @Valid @RequestBody final CreateReviewDTO reviewDTO) {
        checkUserId(userId, reviewDTO.getCreatedById());
        ReviewResponseDTO responseDTO = reviewService.create(reviewDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{reviewId}")
    @Override
    public ResponseEntity<ReviewResponseDTO> update(
        Long userId,
        @NotNull @PathVariable("reviewId") final Long id,
        @Valid @RequestBody final UpdateReviewDTO reviewDTO) {
        ReviewResponseDTO responseDTO = reviewService.update(userId, id, reviewDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{reviewId}/publish")
    @Override
    public ResponseEntity<ReviewResponseDTO> publish(
        @RequestAttribute("userId") final Long userId,
        @NotNull @PathVariable("reviewId") Long id) {

        ReviewResponseDTO responseDTO = reviewService.publish(userId, id);
        return ResponseEntity.ok(responseDTO);
    }
}
