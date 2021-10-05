package com.adasoft.tis.controllera;

import com.adasoft.tis.controllers.impl.ProposalRestControllerImpl;
import com.adasoft.tis.core.exceptions.EntityNotFoundException;
import com.adasoft.tis.core.exceptions.ErrorResponse;
import com.adasoft.tis.domain.Proposal;
import com.adasoft.tis.dto.proposal.CreateProposalDTO;
import com.adasoft.tis.dto.proposal.ProposalResponseDTO;
import com.adasoft.tis.dto.proposal.UpdateProposalDTO;
import com.adasoft.tis.services.ProposalService;
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

@WebMvcTest(ProposalRestControllerImpl.class)
class ProposalRestControllerImplTest {


    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProposalService proposalService;

    private static final String BASE_URL = "/proposals";
    private static final long ID = 1234561819192632054L;
    private static final long CREATED_BY_ID= 859824510526320544L;
    private static final String PART_A = "files/company1PartA.zip";
    private static final String PART_B = "files/company1PartB.zip";

    @Test
    void createProposalSuccesfully() throws Exception{
        CreateProposalDTO proposalDTO = new CreateProposalDTO();
        proposalDTO.setCreateById(CREATED_BY_ID);
        proposalDTO.setPartA(PART_A);
        proposalDTO.setPartB(PART_B);
        ProposalResponseDTO responseDTO = new ProposalResponseDTO();
        responseDTO.setId(ID);
        responseDTO.setPartA(PART_A);
        responseDTO.setPartB(PART_B);
        responseDTO.setCreatedAt(proposalDTO.getCreatedAt());
        responseDTO.setUpdatedAt(proposalDTO.getUpdatedAt());
        responseDTO.setDeleted(proposalDTO.isDeleted());
        when(proposalService.create(any())).thenReturn(responseDTO);

        mvc.perform(post(BASE_URL).contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(proposalDTO)))
            .andExpect(status().isCreated())
            .andExpect(content().json(objectMapper.writeValueAsString(responseDTO)));
        
    }
    @Test
    void createdProposalBadRequest() throws Exception {
        CreateProposalDTO proposalDTO = new CreateProposalDTO();

        mvc.perform(post(BASE_URL).contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(proposalDTO)))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("title").value("Las validaciones de la entidad no han pasado."));
    }

    @Test
    void updateProposalSuccessfully() throws Exception {
        UpdateProposalDTO proposalDTO = new UpdateProposalDTO();
        proposalDTO.setPartA(PART_A);
        proposalDTO.setDeleted(false);

        ProposalResponseDTO responseDTO = new ProposalResponseDTO();
        responseDTO.setId(ID);
        responseDTO.setCreatedById(CREATED_BY_ID);
        responseDTO.setCreatedAt(proposalDTO.getUpdatedAt());
        responseDTO.setUpdatedAt(proposalDTO.getUpdatedAt());
        responseDTO.setDeleted(proposalDTO.isDeleted());

        when(proposalService.update(any(), any())).thenReturn(responseDTO);

        mvc.perform(put(String.format("%s/{proposalId}", BASE_URL), ID).contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(proposalDTO)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(responseDTO)));
    }

    @Test
    void updateProposalBadRequest() throws Exception {
        UpdateProposalDTO proposalDTO = new UpdateProposalDTO();

        mvc.perform(put(String.format("%s/{proposalId}", BASE_URL), ID).contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(proposalDTO)))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("title").value("Las validaciones de la entidad no han pasado."));
    }

    @Test
    void updateProposalNotFound() throws Exception {
        UpdateProposalDTO updateProposalDTO = new UpdateProposalDTO();

        updateProposalDTO.setDeleted(false);

        when(proposalService.update(any(), any())).thenThrow(new EntityNotFoundException(Proposal.class, ID));

        ErrorResponse errorResponse = ErrorResponse.builder()
            .title("No se pudo encontrar la entidad")
            .message(String.format("Proposal con id %d no se pudo encontrar o no existe.", ID))
            .build();

        mvc.perform(put(String.format("%s/{proposalId}", BASE_URL), ID).contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updateProposalDTO)))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(errorResponse)));
    }

}
