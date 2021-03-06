package com.adasoft.tis.controllers;

import com.adasoft.tis.controllers.impl.ProposalRestControllerImpl;
import com.adasoft.tis.core.exceptions.EntityNotFoundException;
import com.adasoft.tis.core.exceptions.ErrorResponse;
import com.adasoft.tis.core.utils.JWTProvider;
import com.adasoft.tis.domain.Proposal;
import com.adasoft.tis.dto.proposal.CreateProposalDTO;
import com.adasoft.tis.dto.proposal.ProposalResponseDTO;
import com.adasoft.tis.dto.proposal.UpdateProposalDTO;
import com.adasoft.tis.services.ProposalService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProposalRestControllerImpl.class)
class ProposalRestControllerImplTest {


    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private JWTProvider jwtProvider;
    @MockBean
    private ProposalService proposalService;


    private static final String BASE_URL = "/proposals";
    private static final String X_TOKEN = "X-Token";
    private static final String TOKEN_VALUE = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MX0.fhc3wykrAnRpcKApKhXiahxaOe8PSHatad31NuIZ0Zg";
    private static final Long USER_ID = 859824510526320544L;
    private static final Long ID = 12L;
    private static final Long CREATED_BY_ID = 859824510526320544L;
    private static final String PART = "Parte A";
    private static final String FILE_URL = "files/company1PartA.zip";
    private static final Long ADVISER_ID = 123L;
    private static CreateProposalDTO createDTO;
    private static ProposalResponseDTO responseDTO;

    @BeforeAll
    static void setup() {
        createDTO = new CreateProposalDTO();
        createDTO.setCreatedById(CREATED_BY_ID);
        createDTO.setPart(PART);
        createDTO.setFileUrl(FILE_URL);
        createDTO.setAdviserId(ADVISER_ID);

        responseDTO = new ProposalResponseDTO();
        responseDTO.setCreatedById(CREATED_BY_ID);
        responseDTO.setPart(PART);
        responseDTO.setFileUrl(FILE_URL);
        responseDTO.setAdviserId(ADVISER_ID);

    }

    @Test
    void getProposalSuccesfully() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        when(proposalService.getById(any())).thenReturn(responseDTO);

        mvc.perform(get(BASE_URL + "/{proposalId}", ID).header(X_TOKEN, TOKEN_VALUE))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(responseDTO)));

    }

    @Test
    void getProposalNotFound() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        UpdateProposalDTO updateProposalDTO = new UpdateProposalDTO();

        when(proposalService.getById(any())).thenThrow(new EntityNotFoundException(Proposal.class, ID));

        ErrorResponse errorResponse = ErrorResponse.builder()
            .title("No se pudo encontrar la entidad")
            .message(String.format("Proposal con id %d no se pudo encontrar o no existe.", ID))
            .build();

        mvc.perform(get(String.format("%s/{proposalId}", BASE_URL), ID).header(X_TOKEN, TOKEN_VALUE))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(errorResponse)));
    }

    @Test
    void createProposalSuccessfully() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        responseDTO.setCreatedAt(createDTO.getCreatedAt());
        responseDTO.setUpdatedAt(createDTO.getUpdatedAt());
        responseDTO.setDeleted(createDTO.isDeleted());

        when(proposalService.create(any())).thenReturn(responseDTO);

        mvc.perform(post(BASE_URL)
                .header(X_TOKEN, TOKEN_VALUE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createDTO)))
            .andExpect(status().isCreated())
            .andExpect(content().json(objectMapper.writeValueAsString(responseDTO)));
    }

    @Test
    void createdProposalBadRequest() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        CreateProposalDTO badProposalDTO = new CreateProposalDTO();

        mvc.perform(post(BASE_URL)
                .header(X_TOKEN, TOKEN_VALUE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(badProposalDTO)))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("title").value("Las validaciones de la entidad no han pasado."));
    }
}
