package com.adasoft.tis.controllers;

import com.adasoft.tis.controllers.impl.ReviewRestControllerImpl;
import com.adasoft.tis.core.exceptions.EntityNotFoundException;
import com.adasoft.tis.core.exceptions.ErrorResponse;
import com.adasoft.tis.domain.Review;
import com.adasoft.tis.dto.review.CreateReviewDTO;
import com.adasoft.tis.dto.review.ReviewResponseDTO;
import com.adasoft.tis.dto.review.UpdateReviewDTO;
import com.adasoft.tis.services.ReviewService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReviewRestControllerImpl.class)
class ReviewRestControllerImplTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ReviewService reviewService;

    private static final String BASE_URL = "/reviews";

    private static final long ID = 1996128482800373344L;
    private static final int SCORE = 100;
    private static final String COMMENT = "Este es un comentario.";
    private static final long CREATED_BY_ID = 982451052632054485L;

    @Test
    void createReviewSuccessfully() throws Exception {
        CreateReviewDTO reviewDTO = new CreateReviewDTO();
        reviewDTO.setCreatedById(CREATED_BY_ID);
        reviewDTO.setTotalScore(SCORE);
        reviewDTO.setComment(COMMENT);
        ReviewResponseDTO responseDTO = new ReviewResponseDTO();
        responseDTO.setId(ID);
        responseDTO.setTotalScore(SCORE);
        responseDTO.setComment(COMMENT);
        responseDTO.setCreatedById(CREATED_BY_ID);
        responseDTO.setCreatedAt(reviewDTO.getCreatedAt());
        responseDTO.setUpdatedAt(reviewDTO.getUpdatedAt());
        responseDTO.setDeleted(reviewDTO.isDeleted());

        when(reviewService.create(any())).thenReturn(responseDTO);

        mvc.perform(post(BASE_URL).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reviewDTO)))
            .andExpect(status().isCreated())
            .andExpect(content().json(objectMapper.writeValueAsString(responseDTO)));
    }

    @Test
    void createdReviewBadRequest() throws Exception {
        CreateReviewDTO reviewDTO = new CreateReviewDTO();

        mvc.perform(post(BASE_URL).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reviewDTO)))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("title").value("Las validaciones de la entidad no han pasado."));
    }

    @Test
    void updateReviewSuccessfully() throws Exception {
        UpdateReviewDTO reviewDTO = new UpdateReviewDTO();
        // reviewDTO.set(SCORE);
        reviewDTO.setComment(COMMENT);
        // reviewDTO.setDeleted(false);

        ReviewResponseDTO responseDTO = new ReviewResponseDTO();
        responseDTO.setId(ID);
        responseDTO.setTotalScore(SCORE);
        responseDTO.setComment(COMMENT);
        responseDTO.setCreatedById(CREATED_BY_ID);
        responseDTO.setCreatedAt(reviewDTO.getUpdatedAt());
        responseDTO.setUpdatedAt(reviewDTO.getUpdatedAt());

        when(reviewService.update(any(), any())).thenReturn(responseDTO);

        mvc.perform(put(String.format("%s/{reviewId}", BASE_URL), ID).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reviewDTO)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(responseDTO)));
    }

    @Test
    void updateReviewBadRequest() throws Exception {
        UpdateReviewDTO reviewDTO = new UpdateReviewDTO();

        mvc.perform(put(String.format("%s/{reviewId}", BASE_URL), ID).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reviewDTO)))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("title").value("Las validaciones de la entidad no han pasado."));
    }

    @Test
    void updateReviewNotFound() throws Exception {
        UpdateReviewDTO updateReviewDTO = new UpdateReviewDTO();
        // updateReviewDTO.setTotalScore(SCORE);
        updateReviewDTO.setComment(COMMENT);
        // updateReviewDTO.setDeleted(false);

        when(reviewService.update(any(), any())).thenThrow(new EntityNotFoundException(Review.class, ID));

        ErrorResponse errorResponse = ErrorResponse.builder()
            .title("No se pudo encontrar la entidad")
            .message(String.format("Review con id %d no se pudo encontrar o no existe.", ID))
            .build();

        mvc.perform(put(String.format("%s/{reviewId}", BASE_URL), ID).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateReviewDTO)))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(errorResponse)));
    }
}