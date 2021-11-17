package com.adasoft.tis.controllers;

import com.adasoft.tis.controllers.impl.CompanyRestControllerImpl;
import com.adasoft.tis.core.exceptions.EntityNotFoundException;
import com.adasoft.tis.core.exceptions.ErrorResponse;
import com.adasoft.tis.core.utils.JWTProvider;
import com.adasoft.tis.domain.Company;
import com.adasoft.tis.domain.Review;
import com.adasoft.tis.dto.company.CompanyResponseDTO;
import com.adasoft.tis.dto.company.UpdateCompanyDTO;
import com.adasoft.tis.dto.review.ReviewCompactResponseDTO;
import com.adasoft.tis.dto.review.ReviewResponseDTO;
import com.adasoft.tis.services.CompanyService;
import com.adasoft.tis.services.ReviewService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CompanyRestControllerImpl.class)
class CompanyRestControllerImplTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private JWTProvider jwtProvider;
    @MockBean
    private CompanyService companyService;
    @MockBean
    private ReviewService reviewService;

    private static final String BASE_URL = "/companies";
    private static final String X_TOKEN = "X-Token";
    private static final String TOKEN_VALUE = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MX0.fhc3wykrAnRpcKApKhXiahxaOe8PSHatad31NuIZ0Zg";
    private static final Long USER_ID = 1L;
    private static final Long ID = 1L;
    private static final String SHORT = "acme";
    private static final String NAME = "acme company";
    private static final String COMPANY_TYPE = "SRL";
    private static final String ADDRESS = "Jordan y Oquendo";
    private static final String EMAIL = "acme@gmail.com";
    private static final String TELEPHONE = "77777777";
    private static final String[] PARTNERS = {"Violeta Guzman", "Jesus Jimenez", "Leonardo Roldan", "Luis Tapia", "Viviana Tolaba"};
    private static CompanyResponseDTO responseDTO;
    private static UpdateCompanyDTO updateDTO;


    @BeforeAll
    static void setup() {
        responseDTO = new CompanyResponseDTO();
        responseDTO.setId(ID);
        responseDTO.setShortname(SHORT);
        responseDTO.setName(NAME);
        responseDTO.setCompanyType(COMPANY_TYPE);
        responseDTO.setAddress(ADDRESS);
        responseDTO.setEmail(EMAIL);
        responseDTO.setPartners(Arrays.asList(PARTNERS));
        updateDTO = new UpdateCompanyDTO();
        updateDTO.setAddress(ADDRESS + "23");
        updateDTO.setTelephone(TELEPHONE);
        updateDTO.setPartners(Arrays.asList(PARTNERS).subList(0, 3));
    }

    @Test
    void getCompanySuccessfully() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        when(companyService.getById(any())).thenReturn(responseDTO);

        mvc.perform(get(String.format("%s/{companyId}", BASE_URL), ID).header(X_TOKEN, TOKEN_VALUE))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(responseDTO)));
    }

    @Test
    void getCompanyNotFound() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        when(companyService.getById(any())).thenThrow(new EntityNotFoundException(Company.class, ID));

        ErrorResponse errorResponse = ErrorResponse.builder()
            .title("No se pudo encontrar la entidad")
            .message(String.format("Company con id %d no se pudo encontrar o no existe.", ID))
            .build();

        mvc.perform(get(String.format("%s/{companyId}", BASE_URL), ID).header(X_TOKEN, TOKEN_VALUE))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(errorResponse)));
    }

    @Test
    void getAllIsOk() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        Collection<CompanyResponseDTO> companies = new HashSet<>();
        companies.add(responseDTO);
        when(companyService.getAll()).thenReturn(companies);

        mvc.perform(get(BASE_URL).header(X_TOKEN, TOKEN_VALUE))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(companies)));
    }

    @Test
    void updateCompanySuccesfully() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        when(companyService.update(any(), any())).thenReturn(responseDTO);

        mvc.perform(put(String.format("%s/{companyId}", BASE_URL), ID)
                .header(X_TOKEN, TOKEN_VALUE).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateDTO)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(responseDTO)));
    }

    @Test
    void updateCompanyBadRequest() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        mvc.perform(put(String.format("%s/{companyId}", BASE_URL), ID).header(X_TOKEN, TOKEN_VALUE))
            .andExpect(status().isBadRequest());

    }

    @Test
    void updateCompanyNotFound() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        when(companyService.update(any(), any())).thenThrow(new EntityNotFoundException(Company.class, ID));

        mvc.perform(put(String.format("%s/{companyId}", BASE_URL), ID)
                .header(X_TOKEN, TOKEN_VALUE).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateDTO)))
            .andExpect(status().isNotFound());
    }

    @Test
    void getCompanyReviewsSuccess() throws Exception {
        Collection<ReviewCompactResponseDTO> reviews = new HashSet<>();
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        when(reviewService.getCompanyReviews(any())).thenReturn(reviews);

        mvc.perform(get(String.format("%s/{companyId}/reviews", BASE_URL), ID)
                .header(X_TOKEN, TOKEN_VALUE))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(reviews)));

    }

    @Test
    void getCompanyReviewsCompanyNotFound() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        when(reviewService.getCompanyReviews(any())).thenThrow(new EntityNotFoundException(Company.class, ID));

        mvc.perform(get(String.format("%s/{companyId}/reviews", BASE_URL), ID)
            .header(X_TOKEN, TOKEN_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void getCompanyReviewsUnauthorized() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(2L);


        mvc.perform(get(String.format("%s/{companyId}/reviews", BASE_URL), ID)
            .header(X_TOKEN, TOKEN_VALUE)).andExpect(status().isUnauthorized());

    }

    @Test
    void getReviewSuccesfully() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(ID);
        ReviewResponseDTO reviewResponse = new ReviewResponseDTO();
        when(reviewService.getCompanyReview(any(), any())).thenReturn(reviewResponse);
        mvc.perform(get(String.format("%s/{companyId}/reviews/{reviewID}", BASE_URL), ID, 1)
                .header(X_TOKEN, TOKEN_VALUE))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(reviewResponse)));
    }

    @Test
    void getReviewUnauthorized() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(2L);
        mvc.perform(get(String.format("%s/{companyId}/reviews/{reviewID}", BASE_URL), ID, 1)
                .header(X_TOKEN, TOKEN_VALUE))
            .andExpect(status().isUnauthorized());
    }

    @Test
    void getReviewCompanyNotFound() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(ID);
        ReviewResponseDTO reviewResponse = new ReviewResponseDTO();
        when(reviewService.getCompanyReview(any(), any()))
            .thenThrow(new EntityNotFoundException(Company.class, ID));
        mvc.perform(get(String.format("%s/{companyId}/reviews/{reviewID}", BASE_URL), ID, 1)
                .header(X_TOKEN, TOKEN_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    void getReviewNotFound() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(ID);
        ReviewResponseDTO reviewResponse = new ReviewResponseDTO();
        when(reviewService.getCompanyReview(any(), any()))
            .thenThrow(new EntityNotFoundException(Review.class, 1));
        mvc.perform(get(String.format("%s/{companyId}/reviews/{reviewID}", BASE_URL), ID, 1)
                .header(X_TOKEN, TOKEN_VALUE))
            .andExpect(status().isNotFound());
    }
}