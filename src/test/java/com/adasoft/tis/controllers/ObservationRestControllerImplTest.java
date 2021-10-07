package com.adasoft.tis.controllers;

import com.adasoft.tis.controllers.impl.ObservationRestControllerImpl;
import com.adasoft.tis.core.exceptions.EntityNotFoundException;
import com.adasoft.tis.core.exceptions.ErrorResponse;
import com.adasoft.tis.domain.Observation;
import com.adasoft.tis.dto.observation.CreateObservationDTO;
import com.adasoft.tis.dto.observation.ObservationResponseDTO;
import com.adasoft.tis.dto.observation.UpdateObservationDTO;
import com.adasoft.tis.dto.proposal.UpdateProposalDTO;
import com.adasoft.tis.services.ObservationService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ObservationRestControllerImpl.class)
class ObservationRestControllerImplTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ObservationService observationService;

    private static final String BASE_URL = "/observations";
    private static final long ID = 15615;
    private static final String TITLE = "SECCION 2";
    private static final String DESCRIPTION = "Descripcion de la observacion";
    private static final long PROPOSAL_ID = 2;

    private static  CreateObservationDTO observationDTO;
    private static ObservationResponseDTO responseDTO;
    @BeforeAll
    static void setup(){
        observationDTO = new CreateObservationDTO();
        observationDTO.setTitle(TITLE);
        observationDTO.setDescription(DESCRIPTION);
        observationDTO.setProposalId(PROPOSAL_ID);
        responseDTO = new ObservationResponseDTO();
        responseDTO.setTitle(TITLE);
        responseDTO.setDescription(DESCRIPTION);
        responseDTO.setProposalId(PROPOSAL_ID);
    }

    @Test
    void createObservationSuccessfully() throws Exception {

        responseDTO.setCreatedAt(observationDTO.getCreatedAt());
        responseDTO.setUpdatedAt(observationDTO.getUpdatedAt());
        responseDTO.setDeleted(observationDTO.isDeleted());

        when(observationService.create(any(),any())).thenReturn(responseDTO);

        mvc.perform(post(BASE_URL).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(observationDTO)))
            .andExpect(status().isCreated())
            .andExpect(content().json(objectMapper.writeValueAsString(responseDTO)));
    }

    @Test
    void createdObservationBadRequest() throws Exception {
        CreateObservationDTO badObservationDTO = new CreateObservationDTO();

        mvc.perform(post(BASE_URL).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(badObservationDTO)))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("title").value("Las validaciones de la entidad no han pasado."));
    }
    @Test
    void getObservationSuccesfully() throws Exception{
        when(observationService.getById(any())).thenReturn(responseDTO);

        mvc.perform(get(BASE_URL+"/{proposalId}",ID)
        ).andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(responseDTO)));

    }

    @Test
    void getObservationNotFound() throws Exception {
        UpdateObservationDTO updateObservationDTO = new UpdateObservationDTO();

        updateObservationDTO.setDeleted(false);

        when(observationService.getById(any())).thenThrow(new EntityNotFoundException(Observation.class, ID));

        ErrorResponse errorResponse = ErrorResponse.builder()
            .title("No se pudo encontrar la entidad")
            .message(String.format("Observation con id %d no se pudo encontrar o no existe.", ID))
            .build();

        mvc.perform(get(String.format("%s/{observation}", BASE_URL), ID)
        ).andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(errorResponse)));
    }

    @Test
    void updateObservationSuccessfully() throws Exception {
        UpdateObservationDTO observationDTO = new UpdateObservationDTO();
        observationDTO.setTitle(TITLE);
        observationDTO.setDescription(DESCRIPTION);
        observationDTO.setDeleted(false);
                
        responseDTO.setCreatedAt(observationDTO.getUpdatedAt());
        responseDTO.setUpdatedAt(observationDTO.getUpdatedAt());
        responseDTO.setDeleted(observationDTO.getDeleted());

        when(observationService.update(any(), any())).thenReturn(responseDTO);

        mvc.perform(put(String.format("%s/{observationId}", BASE_URL), ID).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(observationDTO)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(responseDTO)));
    }

    @Test
    void updateObservationBadRequest() throws Exception {
        UpdateObservationDTO observationDTO = new UpdateObservationDTO();

        mvc.perform(put(String.format("%s/{observationId}", BASE_URL), ID).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(observationDTO)))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("title").value("Las validaciones de la entidad no han pasado."));
    }

    @Test
    void updateObservationNotFound() throws Exception {
        UpdateObservationDTO updateObservationDTO = new UpdateObservationDTO();
        updateObservationDTO.setTitle(TITLE);
        updateObservationDTO.setDescription(DESCRIPTION);
        updateObservationDTO.setDeleted(false);

        when(observationService.update(any(), any())).thenThrow(new EntityNotFoundException(Observation.class, ID));

        ErrorResponse errorResponse = ErrorResponse.builder()
            .title("No se pudo encontrar la entidad")
            .message(String.format("Observation con id %d no se pudo encontrar o no existe.", ID))
            .build();

        mvc.perform(put(String.format("%s/{observationId}", BASE_URL), ID).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateObservationDTO)))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(errorResponse)));
    }
}