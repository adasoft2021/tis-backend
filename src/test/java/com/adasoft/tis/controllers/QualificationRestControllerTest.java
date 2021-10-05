package com.adasoft.tis.controllers;

import com.adasoft.tis.controllers.impl.QualificationRestControllerImpl;
import com.adasoft.tis.core.exceptions.EntityNotFoundException;
import com.adasoft.tis.core.exceptions.ErrorResponse;
import com.adasoft.tis.domain.Qualification;
import com.adasoft.tis.dto.qualification.CreateQualificationDTO;
import com.adasoft.tis.dto.qualification.QualificationResponseDTO;
import com.adasoft.tis.dto.qualification.UpdateQualificationDTO;
import com.adasoft.tis.services.QualificationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
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

@WebMvcTest(QualificationRestControllerImpl.class)
class QualificationRestControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private QualificationService qualificationService;

    private static final String BASE_URL = "/qualifications";

    private static final CreateQualificationDTO CREATE_DTO = new CreateQualificationDTO();
    private static final UpdateQualificationDTO UPDATE_DTO = new UpdateQualificationDTO();
    private static final QualificationResponseDTO RESPONSE_DTO = new QualificationResponseDTO();

    private static final String DESCRIPTION = "Cumplimiento de especificaciones del proponente";
    private static final int SCORE = 14;
    private static final int MAX_SCORE = 15;

    private static final long ID = 6120175841382441426L;

    @BeforeAll
    static void setup() {
        CREATE_DTO.setDescription(DESCRIPTION);
        CREATE_DTO.setScore(SCORE);
        CREATE_DTO.setMaxScore(MAX_SCORE);

        UPDATE_DTO.setId(ID);
        UPDATE_DTO.setDeleted(false);
        UPDATE_DTO.setUpdatedAt(CREATE_DTO.getUpdatedAt());
        UPDATE_DTO.setScore(SCORE);

        RESPONSE_DTO.setId(ID);
        RESPONSE_DTO.setCreatedAt(CREATE_DTO.getCreatedAt());
        RESPONSE_DTO.setUpdatedAt(CREATE_DTO.getUpdatedAt());
        RESPONSE_DTO.setDeleted(CREATE_DTO.isDeleted());
        RESPONSE_DTO.setDescription(DESCRIPTION);
        RESPONSE_DTO.setScore(SCORE);
        RESPONSE_DTO.setMaxScore(MAX_SCORE);
    }

    @Test
    void createQualificationSuccessfully() throws Exception {
        when(qualificationService.create(any())).thenReturn(RESPONSE_DTO);

        mvc.perform(post(BASE_URL).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(CREATE_DTO)))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(RESPONSE_DTO)));
    }

    @Test
    void createQualificationBadRequest() throws Exception {
        CreateQualificationDTO qualificationDTO = new CreateQualificationDTO();

        mvc.perform(post(BASE_URL).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(qualificationDTO)))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("title").value("Las validaciones de la entidad no han pasado."));
    }

    @Test
    void updateQualificationSuccessfully() throws Exception {
        when(qualificationService.update(any(), any())).thenReturn(RESPONSE_DTO);

        mvc.perform(put(String.format("%s/{qualificationId}", BASE_URL), ID).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(UPDATE_DTO)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(RESPONSE_DTO)));
    }

    @Test
    void updateQualificationBadRequest() throws Exception {
        UpdateQualificationDTO qualificationDTO = new UpdateQualificationDTO();

        mvc.perform(put(String.format("%s/{qualificationId}", BASE_URL), ID).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(qualificationDTO)))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("title").value("Las validaciones de la entidad no han pasado."));
    }

    @Test
    void updateQualificationNotFound() throws Exception {
        when(qualificationService.update(any(), any())).thenThrow(new EntityNotFoundException(Qualification.class, ID));

        ErrorResponse errorResponse = ErrorResponse.builder()
            .title("No se pudo encontrar la entidad")
            .message(String.format("Qualification con id %d no se pudo encontrar o no existe.", ID))
            .build();

        mvc.perform(put(String.format("%s/{reviewId}", BASE_URL), ID).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(UPDATE_DTO)))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(errorResponse)));
    }
}