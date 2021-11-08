package com.adasoft.tis.controllers;

import com.adasoft.tis.controllers.impl.SpaceRestControllerImpl;
import com.adasoft.tis.core.exceptions.EntityNotFoundException;
import com.adasoft.tis.core.exceptions.ErrorResponse;
import com.adasoft.tis.core.utils.JWTProvider;
import com.adasoft.tis.domain.Company;
import com.adasoft.tis.domain.FileEntity;
import com.adasoft.tis.domain.Space;
import com.adasoft.tis.dto.spaceAnswer.CreateSpaceAnswerDTO;
import com.adasoft.tis.dto.spaceAnswer.SpaceAnswerResponseDTO;
import com.adasoft.tis.services.SpaceAnswerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SpaceRestControllerImpl.class)
class SpaceRestControllerImplTest {


    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private JWTProvider jwtProvider;
    @MockBean
    private SpaceAnswerService spaceAnswerService;

    private static final String BASE_URL = "/spaces";
    private static final String X_TOKEN = "X-Token";
    private static final String TOKEN_VALUE = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MX0.fhc3wykrAnRpcKApKhXiahxaOe8PSHatad31NuIZ0Zg";
    private static final Long USER_ID = 3L;
    private static final Long ID = 1L;
    private static final String BASE_URL_ANSWERS = BASE_URL + "/" + ID;
    private static final Long CREATED_BY_ID = 3L;
    private static final SpaceAnswerResponseDTO RESPONSE_DTO = new SpaceAnswerResponseDTO();
    private static final CreateSpaceAnswerDTO CREATE_DTO = new CreateSpaceAnswerDTO();

    @BeforeAll
    static void setUp() {
        CREATE_DTO.setSpaceId(ID);
        CREATE_DTO.setCreatedById(CREATED_BY_ID);
        FileEntity file = new FileEntity();
        file.setName("archivo.pdf");
        file.setUrl("/files/archivo.pdf");
        CREATE_DTO.setFiles(List.of(file));
        RESPONSE_DTO.setSpaceId(ID);
        RESPONSE_DTO.setCompanyName("acme");
        RESPONSE_DTO.setFiles(CREATE_DTO.getFiles());
    }

    @Test
    void createSpaceAnswerSuccessfully() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        when(spaceAnswerService.create(any(), any())).thenReturn(RESPONSE_DTO);

        mvc.perform(post(BASE_URL_ANSWERS).header(X_TOKEN, TOKEN_VALUE).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(CREATE_DTO)))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(RESPONSE_DTO)));
    }

    @Test
    void createSpaceAnswerBadRequest() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        CreateSpaceAnswerDTO createBadDTO = new CreateSpaceAnswerDTO();

        mvc.perform(post(BASE_URL_ANSWERS).header(X_TOKEN, TOKEN_VALUE).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createBadDTO)))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("title").value("Las validaciones de la entidad no han pasado."));
    }

    @Test
    void createSpaceAnswerSpaceNotFound() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        when(spaceAnswerService.create(any(), any())).thenThrow(new EntityNotFoundException(Space.class, ID));

        ErrorResponse errorResponse = ErrorResponse.builder()
            .title("No se pudo encontrar la entidad")
            .message(String.format("Space con id %d no se pudo encontrar o no existe.", ID))
            .build();

        mvc.perform(post(BASE_URL_ANSWERS).header(X_TOKEN, TOKEN_VALUE).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(CREATE_DTO)))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(errorResponse)));
    }

    @Test
    void createSpaceAnswerCompanyNotFound() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        when(spaceAnswerService.create(any(), any())).thenThrow(new EntityNotFoundException(Company.class, CREATED_BY_ID));

        ErrorResponse errorResponse = ErrorResponse.builder()
            .title("No se pudo encontrar la entidad")
            .message(String.format("Company con id %d no se pudo encontrar o no existe.", CREATED_BY_ID))
            .build();

        mvc.perform(post(BASE_URL_ANSWERS).header(X_TOKEN, TOKEN_VALUE).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(CREATE_DTO)))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(errorResponse)));
    }
}