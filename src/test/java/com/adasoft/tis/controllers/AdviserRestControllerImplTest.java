package com.adasoft.tis.controllers;

import com.adasoft.tis.controllers.impl.AdviserRestControllerImpl;
import com.adasoft.tis.core.exceptions.EntityNotFoundException;
import com.adasoft.tis.core.exceptions.ErrorResponse;
import com.adasoft.tis.core.utils.JWTProvider;
import com.adasoft.tis.domain.Adviser;
import com.adasoft.tis.domain.Space;
import com.adasoft.tis.dto.classCode.ClassCodeResponseDTO;
import com.adasoft.tis.dto.space.SpaceCompactResponseDTO;
import com.adasoft.tis.dto.spaceAnswer.SemesterSpaceAnswersResponseDTO;
import com.adasoft.tis.dto.spaceAnswer.SpaceAnswerResponseDTO;
import com.adasoft.tis.services.AdviserService;
import com.adasoft.tis.services.ClassCodeService;
import com.adasoft.tis.services.SpaceAnswerService;
import com.adasoft.tis.services.SpaceService;
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
import java.util.LinkedList;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdviserRestControllerImpl.class)
class AdviserRestControllerImplTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private JWTProvider jwtProvider;
    @MockBean
    private AdviserService adviserService;
    @MockBean
    private ClassCodeService classCodeService;
    @MockBean
    private SpaceAnswerService spaceAnswerService;
    @MockBean
    private SpaceService spaceService;

    private static final String BASE_URL = "/advisers";
    private static final String X_TOKEN = "X-Token";
    private static final String TOKEN_VALUE = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MX0.fhc3wykrAnRpcKApKhXiahxaOe8PSHatad31NuIZ0Zg";
    private static final Long USER_ID = 1L;
    private static final Long ID = 1L;
    private static final String CODE = "asd-asd-asd";
    private static final Long SPACE_ID = 1L;

    private static ClassCodeResponseDTO classCodeDTO;

    @BeforeAll
    static void setup() {
        classCodeDTO = new ClassCodeResponseDTO();
        classCodeDTO.setCode(CODE);
    }

    @Test
    void createClassCodeSuccessfully() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        when(classCodeService.create(any())).thenReturn(classCodeDTO);

        mvc.perform(post(String.format("%s/{adviserId}/class-code", BASE_URL), ID).header(X_TOKEN, TOKEN_VALUE))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(classCodeDTO)));
    }

    @Test
    void createClassCodeAdviserNotFound() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        when(classCodeService.create(any())).thenThrow(new EntityNotFoundException(Adviser.class, ID));

        ErrorResponse errorResponse = ErrorResponse.builder()
            .title("No se pudo encontrar la entidad")
            .message(String.format("Adviser con id %d no se pudo encontrar o no existe.", ID))
            .build();

        mvc.perform(post(String.format("%s/{adviserId}/class-code", BASE_URL), ID).header(X_TOKEN, TOKEN_VALUE))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(errorResponse)));
    }

    @Test
    void createClassCodeUnauthorized() throws Exception {


        ErrorResponse errorResponse = ErrorResponse.builder()
            .title("El proceso no puede continuar")
            .message("Usted no tiene autorización para realizar esta acción.")
            .build();

        mvc.perform(post(String.format("%s/{adviserId}/class-code", BASE_URL), ID).header(X_TOKEN, ""))
            .andExpect(status().isUnauthorized()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(errorResponse)));
    }

    @Test
    void getSpaceAnswersSuccesfully() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);

        SpaceAnswerResponseDTO answer = new SpaceAnswerResponseDTO();
        answer.setCompanyName("Company");
        answer.setSpaceId(SPACE_ID);
        answer.setId(1L);
        SpaceAnswerResponseDTO[] answers = {answer};

        when(spaceAnswerService.getBySpaceId(any(), any()))
            .thenReturn(Arrays.stream(answers).collect(Collectors.toSet()));

        mvc.perform(get(String.format("%s/{adviserId}/spaces/{spaceId}", BASE_URL), ID, SPACE_ID)
                .header(X_TOKEN, TOKEN_VALUE))
            .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(answers)));
    }

    @Test
    void getSpaceAnswersAdviserNotExist() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        when(spaceAnswerService.getBySpaceId(any(), any())).thenThrow(new EntityNotFoundException(Adviser.class, ID));

        ErrorResponse errorResponse = ErrorResponse.builder()
            .title("No se pudo encontrar la entidad")
            .message(String.format("Adviser con id %d no se pudo encontrar o no existe.", ID))
            .build();

        mvc.perform(get(String.format("%s/{adviserId}/spaces/{spaceId}", BASE_URL), ID, SPACE_ID)
                .header(X_TOKEN, TOKEN_VALUE))
            .andExpect(status().isNotFound()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(errorResponse)));
    }

    @Test
    void getSpaceAnswersSpaceNotExist() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        when(spaceAnswerService.getBySpaceId(any(), any())).thenThrow(new EntityNotFoundException(Space.class, SPACE_ID));

        ErrorResponse errorResponse = ErrorResponse.builder()
            .title("No se pudo encontrar la entidad")
            .message(String.format("Space con id %d no se pudo encontrar o no existe.", SPACE_ID))
            .build();

        mvc.perform(get(String.format("%s/{adviserId}/spaces/{spaceId}", BASE_URL), ID, SPACE_ID)
                .header(X_TOKEN, TOKEN_VALUE))
            .andExpect(status().isNotFound()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(errorResponse)));
    }

    @Test
    void getSpacesSuccess() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        HashSet<SpaceCompactResponseDTO> spacesDTOs = new HashSet<>();
        when(spaceService.getAdviserSpaces(any(), any())).thenReturn(spacesDTOs);

        mvc.perform(get(String.format("%s/{adviserId}/spaces", BASE_URL), ID)
                .queryParam("spaceType", "ALL").header(X_TOKEN, TOKEN_VALUE))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(spacesDTOs)));
    }

    @Test
    void getSpacesUnauthorized() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(45L);
        HashSet<SpaceCompactResponseDTO> spacesDTOs = new HashSet<>();

        mvc.perform(get(String.format("%s/{adviserId}/spaces", BASE_URL), ID)
                .queryParam("spaceType", "ALL").header(X_TOKEN, TOKEN_VALUE))
            .andExpect(status().isUnauthorized());
    }

    @Test
    void getProposalsHistorySuccess() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        Collection<SemesterSpaceAnswersResponseDTO> response = new LinkedList<>();
        when(spaceAnswerService.getAdviserHistory(any())).thenReturn(response);

        mvc.perform(get(String.format("%s/{adviserId}/proposals/history", BASE_URL), ID)
                .header(X_TOKEN, TOKEN_VALUE))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    void getProposalsHistoryUnauthorized() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(87L);

        mvc.perform(get(String.format("%s/{adviserId}/proposals/history", BASE_URL), ID)
                .header(X_TOKEN, TOKEN_VALUE))
            .andExpect(status().isUnauthorized());
    }
}