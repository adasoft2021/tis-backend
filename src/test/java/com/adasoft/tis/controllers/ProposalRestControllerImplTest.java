package com.adasoft.tis.controllers;

import com.adasoft.tis.controllers.impl.ProposalRestControllerImpl;
import com.adasoft.tis.core.exceptions.EntityNotFoundException;
import com.adasoft.tis.core.exceptions.ErrorResponse;
import com.adasoft.tis.domain.Proposal;
import com.adasoft.tis.dto.proposal.CreateProposalDTO;
import com.adasoft.tis.dto.proposal.ProposalResponseDTO;
import com.adasoft.tis.dto.proposal.UpdateProposalDTO;
import com.adasoft.tis.repository.ProposalRepository;
import com.adasoft.tis.services.ProposalService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    private static final long ID = 12L;
    private static final long CREATED_BY_ID= 859824510526320544L;
    private static final Proposal.Part PART = Proposal.Part.A;
    private static final String FILE_URL= "files/company1PartA.zip";
    private static final long REVIEW_ID = 1L;
    private static CreateProposalDTO createDTO;
    private static ProposalResponseDTO responseDTO;

    @BeforeAll
    static void setup(){
        createDTO = new CreateProposalDTO();
        createDTO.setCreatedById(CREATED_BY_ID);
        createDTO.setPart(PART);
        createDTO.setFileUrl(FILE_URL);
        createDTO.setReviewId(REVIEW_ID);

        responseDTO = new ProposalResponseDTO();
        responseDTO.setCreatedById(CREATED_BY_ID);
        responseDTO.setPart(PART);
        responseDTO.setFileUrl(FILE_URL);
        responseDTO.setReviewId(REVIEW_ID);

    }

    @Test
    void getProposalSuccesfully() throws Exception{
        when(proposalService.getById(any())).thenReturn(responseDTO);

        mvc.perform(get(BASE_URL+"/{proposalId}",ID)
            ).andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(responseDTO)));
        
    }

    @Test
    void getProposalNotFound() throws Exception {
        UpdateProposalDTO updateProposalDTO = new UpdateProposalDTO();

        updateProposalDTO.setDeleted(false);

        when(proposalService.getById(any())).thenThrow(new EntityNotFoundException(Proposal.class, ID));

        ErrorResponse errorResponse = ErrorResponse.builder()
            .title("No se pudo encontrar la entidad")
            .message(String.format("Proposal con id %d no se pudo encontrar o no existe.", ID))
            .build();

        mvc.perform(get(String.format("%s/{proposalId}", BASE_URL), ID)
            ).andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(errorResponse)));
    }

}
