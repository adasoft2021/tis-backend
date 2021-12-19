package com.adasoft.tis.controllers;

import com.adasoft.tis.controllers.impl.ReviewRestControllerImpl;
import com.adasoft.tis.core.exceptions.DefaultTisDomainException;
import com.adasoft.tis.core.exceptions.EntityNotFoundException;
import com.adasoft.tis.core.exceptions.ErrorResponse;
import com.adasoft.tis.core.utils.JWTProvider;
import com.adasoft.tis.domain.Adviser;
import com.adasoft.tis.domain.Review;
import com.adasoft.tis.dto.qualification.UpdateQualificationDTO;
import com.adasoft.tis.dto.review.CreateReviewDTO;
import com.adasoft.tis.dto.review.ReviewCompactResponseDTO;
import com.adasoft.tis.dto.review.ReviewResponseDTO;
import com.adasoft.tis.dto.review.UpdateReviewDTO;
import com.adasoft.tis.services.ReviewService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReviewRestControllerImpl.class)
class ReviewRestControllerImplTest {


    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private JWTProvider jwtProvider;
    @MockBean
    private ReviewService reviewService;

    private static final String BASE_URL = "/reviews";
    private static final String X_TOKEN = "X-Token";
    private static final String TOKEN_VALUE = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MX0.fhc3wykrAnRpcKApKhXiahxaOe8PSHatad31NuIZ0Zg";
    private static final Long USER_ID = 982451052632054485L;

    private static final CreateReviewDTO CREATE_REVIEW_DTO = new CreateReviewDTO();
    private static final UpdateReviewDTO UPDATE_REVIEW_DTO = new UpdateReviewDTO();
    private static final ReviewResponseDTO REVIEW_RESPONSE_DTO = new ReviewResponseDTO();
    private static final ReviewCompactResponseDTO REVIEW_COMPACT_RESPONSE_DTO = new ReviewCompactResponseDTO();

    private static final long ID = 1996128482800373344L;
    private static final int SCORE = 10;
    private static final String COMMENT = "Este es un comentario.";
    private static final long CREATED_BY_ID = 982451052632054485L;

    @BeforeAll
    static void setup() {
        CREATE_REVIEW_DTO.setCreatedById(CREATED_BY_ID);
        CREATE_REVIEW_DTO.setCompanyId(2L);
        CREATE_REVIEW_DTO.setSpaces(List.of(3L));
        CREATE_REVIEW_DTO.setTitle("titulo");

        Collection<UpdateQualificationDTO> qualificationDTOS = new HashSet<>();
        for (int i = 0; i < 7; i++) {
            UpdateQualificationDTO updateQualificationDTO = new UpdateQualificationDTO();
            updateQualificationDTO.setId(ID + i);
            updateQualificationDTO.setQualificationId(ID + i);
            updateQualificationDTO.setUpdatedAt(CREATE_REVIEW_DTO.getUpdatedAt());
            updateQualificationDTO.setScore(SCORE);
            qualificationDTOS.add(updateQualificationDTO);
        }

        UPDATE_REVIEW_DTO.setId(ID);
        UPDATE_REVIEW_DTO.setUpdatedAt(CREATE_REVIEW_DTO.getUpdatedAt());
        UPDATE_REVIEW_DTO.setComment(COMMENT);
        UPDATE_REVIEW_DTO.setQualifications(new HashSet<>(qualificationDTOS));

        REVIEW_RESPONSE_DTO.setId(ID);
        REVIEW_RESPONSE_DTO.setDeleted(false);
        REVIEW_RESPONSE_DTO.setCreatedAt(CREATE_REVIEW_DTO.getCreatedAt());
        REVIEW_RESPONSE_DTO.setUpdatedAt(CREATE_REVIEW_DTO.getUpdatedAt());
        REVIEW_RESPONSE_DTO.setComment(COMMENT);
        REVIEW_RESPONSE_DTO.setQualifications(new HashSet<>());

        REVIEW_COMPACT_RESPONSE_DTO.setId(ID);
        REVIEW_COMPACT_RESPONSE_DTO.setDeleted(false);
        REVIEW_COMPACT_RESPONSE_DTO.setCreatedAt(CREATE_REVIEW_DTO.getCreatedAt());
        REVIEW_COMPACT_RESPONSE_DTO.setUpdatedAt(CREATE_REVIEW_DTO.getUpdatedAt());
        REVIEW_COMPACT_RESPONSE_DTO.setCompanyName("Patito");
        REVIEW_COMPACT_RESPONSE_DTO.setStatus(Review.Status.CHANGE_ORDER);

    }

    @Test
    void getReviewSuccessfully() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        when(reviewService.get(any())).thenReturn(REVIEW_RESPONSE_DTO);

        mvc.perform(get(String.format("%s/{reviewId}", BASE_URL), ID).header(X_TOKEN, TOKEN_VALUE))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(REVIEW_RESPONSE_DTO)));
    }

    @Test
    void getReviewNotFound() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        when(reviewService.get(any())).thenThrow(new EntityNotFoundException(Review.class, ID));

        ErrorResponse errorResponse = ErrorResponse.builder()
            .title("No se pudo encontrar la entidad")
            .message(String.format("Review con id %d no se pudo encontrar o no existe.", ID))
            .build();

        mvc.perform(get(String.format("%s/{reviewId}", BASE_URL), ID).header(X_TOKEN, TOKEN_VALUE))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(errorResponse)));
    }

    @Test
    void createReviewSuccessfully() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        when(reviewService.create(any())).thenReturn(REVIEW_RESPONSE_DTO);

        mvc.perform(post(BASE_URL).contentType(MediaType.APPLICATION_JSON)
                .header(X_TOKEN, TOKEN_VALUE)
                .content(objectMapper.writeValueAsString(CREATE_REVIEW_DTO)))
            .andExpect(status().isCreated())
            .andExpect(content().json(objectMapper.writeValueAsString(REVIEW_RESPONSE_DTO)));
    }

    @Test
    void createdReviewBadRequest() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        CreateReviewDTO reviewDTO = new CreateReviewDTO();

        mvc.perform(post(BASE_URL).header(X_TOKEN, TOKEN_VALUE).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reviewDTO)))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("title").value("Las validaciones de la entidad no han pasado."));
    }

    @Test
    void updateReviewSuccessfully() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        when(reviewService.update(any(), any(), any())).thenReturn(REVIEW_RESPONSE_DTO);

        mvc.perform(put(String.format("%s/{reviewId}", BASE_URL), ID).header(X_TOKEN, TOKEN_VALUE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(UPDATE_REVIEW_DTO)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(REVIEW_RESPONSE_DTO)));
    }

    @Test
    void updateReviewBadRequest() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        UpdateReviewDTO reviewDTO = new UpdateReviewDTO();
        reviewDTO.setQualifications(new HashSet<>());
        mvc.perform(put(String.format("%s/{reviewId}", BASE_URL), ID)
                .header(X_TOKEN, TOKEN_VALUE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reviewDTO)))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("title").value("Las validaciones de la entidad no han pasado."));
    }

    @Test
    void updateReviewNotFound() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        when(reviewService.update(any(), any(), any())).thenThrow(new EntityNotFoundException(Review.class, ID));

        ErrorResponse errorResponse = ErrorResponse.builder()
            .title("No se pudo encontrar la entidad")
            .message(String.format("Review con id %d no se pudo encontrar o no existe.", ID))
            .build();

        mvc.perform(put(String.format("%s/{reviewId}", BASE_URL), ID)
                .header(X_TOKEN, TOKEN_VALUE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(UPDATE_REVIEW_DTO)))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(errorResponse)));
    }

    @Test
    void updateReviewMethodNotAllowed() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        when(reviewService.update(any(), any(), any()))
            .thenThrow(new DefaultTisDomainException(
                HttpStatus.METHOD_NOT_ALLOWED,
                "Usted ya no puede hacer ningún cambio en la entidad Review."));

        ErrorResponse errorResponse = ErrorResponse.builder()
            .title("El proceso no puede continuar")
            .message("Usted ya no puede hacer ningún cambio en la entidad Review.")
            .build();

        mvc.perform(put(String.format("%s/{reviewId}", BASE_URL), ID)
                .header(X_TOKEN, TOKEN_VALUE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(UPDATE_REVIEW_DTO)))
            .andExpect(status().isMethodNotAllowed())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(errorResponse)));
    }

    @Test
    void updateReviewNotAcceptable() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        when(reviewService.update(any(), any(), any()))
            .thenThrow(new DefaultTisDomainException(
                HttpStatus.NOT_ACCEPTABLE,
                "No está permitido editar la entidad Qualification."));

        ErrorResponse errorResponse = ErrorResponse.builder()
            .title("El proceso no puede continuar")
            .message("No está permitido editar la entidad Qualification.")
            .build();

        mvc.perform(put(String.format("%s/{reviewId}", BASE_URL), ID)
                .header(X_TOKEN, TOKEN_VALUE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(UPDATE_REVIEW_DTO)))
            .andExpect(status().isNotAcceptable())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(errorResponse)));
    }

    @Test
    void updateReviewOnlyComment() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        UpdateReviewDTO updateComment = new UpdateReviewDTO();
        updateComment.setComment("Este es el comentario de la revision");
        ReviewResponseDTO reviewWithComment = new ReviewResponseDTO();
        reviewWithComment.setComment(updateComment.getComment());
        when(reviewService.update(any(), any(), any())).thenReturn(reviewWithComment);

        mvc.perform(put(String.format("%s/{reviewId}", BASE_URL), ID).header(X_TOKEN, TOKEN_VALUE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateComment)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(reviewWithComment)));
    }

    @Test
    void publishSuccessfull() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        when(reviewService.publish(any(), any())).thenReturn(REVIEW_RESPONSE_DTO);
        mvc.perform(put(String.format("%s/{reviewId}/publish", BASE_URL), ID)
                .header(X_TOKEN, TOKEN_VALUE))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(REVIEW_RESPONSE_DTO)));
    }

    @Test
    void publishReviewNotFound() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        when(reviewService.publish(any(), any())).thenThrow(new EntityNotFoundException(Review.class, ID));
        mvc.perform(put(String.format("%s/{reviewId}/publish", BASE_URL), ID)
                .header(X_TOKEN, TOKEN_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    void publishAdviserNotFound() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        when(reviewService.publish(any(), any())).thenThrow(new EntityNotFoundException(Adviser.class, USER_ID));
        mvc.perform(put(String.format("%s/{reviewId}/publish", BASE_URL), ID)
                .header(X_TOKEN, TOKEN_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    void publishUnauthorized() throws Exception {

        when(reviewService.publish(any(), any())).thenThrow(new EntityNotFoundException(Review.class, ID));
        mvc.perform(put(String.format("%s/{reviewId}/publish", BASE_URL), ID))
            .andExpect(status().isUnauthorized());
    }

    @Test
    void publishAlreadyPublished() throws Exception {

        when(reviewService.publish(any(), any()))
            .thenThrow(
                new DefaultTisDomainException(
                    HttpStatus.METHOD_NOT_ALLOWED,
                    "Usted ya no puede hacer ningún cambio en la entidad Review."));
        mvc.perform(put(String.format("%s/{reviewId}/publish", BASE_URL), ID)
                .header(X_TOKEN, TOKEN_VALUE))
            .andExpect(status().isMethodNotAllowed());
    }

    @Test
    void tryPublishSuccessfull() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        when(reviewService.finalStatus(any(), any())).thenReturn(REVIEW_COMPACT_RESPONSE_DTO);
        mvc.perform(get(String.format("%s/{reviewId}/publish/try", BASE_URL), ID)
                .header(X_TOKEN, TOKEN_VALUE))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(REVIEW_COMPACT_RESPONSE_DTO)));
    }

    @Test
    void tryPublishReviewNotFound() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        when(reviewService.finalStatus(any(), any())).thenThrow(new EntityNotFoundException(Review.class, ID));
        mvc.perform(get(String.format("%s/{reviewId}/publish/try", BASE_URL), ID)
                .header(X_TOKEN, TOKEN_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    void tryPublishAdviserNotFound() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        when(reviewService.finalStatus(any(), any())).thenThrow(new EntityNotFoundException(Adviser.class, USER_ID));
        mvc.perform(get(String.format("%s/{reviewId}/publish/try", BASE_URL), ID)
                .header(X_TOKEN, TOKEN_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    void tryPublishUnauthorized() throws Exception {

        when(reviewService.finalStatus(any(), any())).thenThrow(new EntityNotFoundException(Review.class, ID));
        mvc.perform(get(String.format("%s/{reviewId}/publish/try", BASE_URL), ID))
            .andExpect(status().isUnauthorized());
    }

    @Test
    void tryPublishAlreadyPublished() throws Exception {

        when(reviewService.finalStatus(any(), any()))
            .thenThrow(
                new DefaultTisDomainException(
                    HttpStatus.METHOD_NOT_ALLOWED,
                    "Usted ya no puede hacer ningún cambio en la entidad Review."));
        mvc.perform(get(String.format("%s/{reviewId}/publish/try", BASE_URL), ID)
                .header(X_TOKEN, TOKEN_VALUE))
            .andExpect(status().isMethodNotAllowed());
    }


    @Test
    void getAdviserReviewsSuccesfully() throws Exception {
        Set<ReviewCompactResponseDTO> reviews = Set.of(new ReviewCompactResponseDTO());
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        when(reviewService.getAdviserReviews(any()))
            .thenReturn(reviews);

        mvc.perform(get(BASE_URL).header(X_TOKEN, TOKEN_VALUE))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(reviews)));
    }

    @Test
    void getAdviserReviewsUnautorized() throws Exception {

        mvc.perform(get(BASE_URL))
            .andExpect(status().isUnauthorized());
    }

}