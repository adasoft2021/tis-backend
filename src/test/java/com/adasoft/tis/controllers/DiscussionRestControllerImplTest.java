package com.adasoft.tis.controllers;

import com.adasoft.tis.controllers.impl.DiscussionRestControllerImpl;
import com.adasoft.tis.core.exceptions.EntityNotFoundException;
import com.adasoft.tis.core.utils.JWTProvider;
import com.adasoft.tis.domain.User;
import com.adasoft.tis.dto.discussion.CreateDiscussionDTO;
import com.adasoft.tis.dto.discussion.DiscussionResponseDTO;
import com.adasoft.tis.services.CommentService;
import com.adasoft.tis.services.DiscussionService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(DiscussionRestControllerImpl.class)
class DiscussionRestControllerImplTest {


    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private JWTProvider jwtProvider;
    @MockBean
    private DiscussionService discussionService;
    @MockBean
    private CommentService commentService;

    private static final String BASE_URL = "/discussions";
    private static final Long USER_ID = 1L;
    private static final String X_TOKEN = "X-Token";
    private static final String TOKEN_VALUE = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MX0.fhc3wykrAnRpcKApKhXiahxaOe8PSHatad31NuIZ0Zg";
    private static final Long COMPANY_ID = 1023L;
    private static final CreateDiscussionDTO CREATE_DTO = new CreateDiscussionDTO();
    private static final DiscussionResponseDTO RESPONSE_DTO = new DiscussionResponseDTO();


    @Test
    void createDiscussionSuccesfully() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        when(discussionService.create(any(), any())).thenReturn(RESPONSE_DTO);

        mvc.perform(post(BASE_URL).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(CREATE_DTO))
                .header(X_TOKEN, TOKEN_VALUE))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(RESPONSE_DTO)));
    }

    @Test
    void createDiscussionUnauthorized() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        when(discussionService.create(any(), any())).thenReturn(RESPONSE_DTO);

        mvc.perform(post(BASE_URL).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(CREATE_DTO)))
            .andExpect(status().isUnauthorized())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    void createDiscussionBadRequest() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        when(discussionService.create(any(), any())).thenReturn(RESPONSE_DTO);

        mvc.perform(post(BASE_URL).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnauthorized())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    void createDiscussionParticipantNotFound() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        when(discussionService.create(any(), any()))
            .thenThrow(new EntityNotFoundException(User.class, COMPANY_ID));
        CreateDiscussionDTO withCompany = new CreateDiscussionDTO();
        withCompany.setCompanyId(COMPANY_ID);

        mvc.perform(post(BASE_URL).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(CREATE_DTO)))
            .andExpect(status().isUnauthorized())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    void createComment() {
    }
}