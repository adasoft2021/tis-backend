package com.adasoft.tis.controllers;

import com.adasoft.tis.controllers.impl.CompanyRestControllerImpl;
import com.adasoft.tis.core.exceptions.EntityNotFoundException;
import com.adasoft.tis.core.exceptions.ErrorResponse;
import com.adasoft.tis.domain.Company;
import com.adasoft.tis.dto.company.CompanyResponseDTO;
import com.adasoft.tis.dto.company.UpdateCompanyDTO;
import com.adasoft.tis.services.CompanyService;
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
    private CompanyService companyService;

    private static final String BASE_URL = "/companies";
    private static final Long ID = 1L;
    private static final String SHORT = "acme";
    private static final String NAME = "acme company";
    private static final String COMPANY_TYPE = "SRL";
    private static final String ADDRESS = "Jordan y Oquendo";
    private static final String EMAIL = "acme@gmail.com";
    private static final String TELEPHONE = "77777777";
    private static final String[] PARTNERS = {"Violeta Guzman", "Jesus Jimenez", "Leonardo Roldan", "Luis Tapia", "Viviana Tolaba" };
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
        when(companyService.getById(any())).thenReturn(responseDTO);

        mvc.perform(get(String.format("%s/{companyId}", BASE_URL), ID))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(responseDTO)));
    }

    @Test
    void getCompanyNotFound() throws Exception {
        when(companyService.getById(any())).thenThrow(new EntityNotFoundException(Company.class, ID));

        ErrorResponse errorResponse = ErrorResponse.builder()
            .title("No se pudo encontrar la entidad")
            .message(String.format("Company con id %d no se pudo encontrar o no existe.", ID))
            .build();

        mvc.perform(get(String.format("%s/{companyId}", BASE_URL), ID))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(errorResponse)));
    }

    @Test
    void getAllIsOk() throws Exception {
        Collection<CompanyResponseDTO> companies = new HashSet<>();
        companies.add(responseDTO);
        when(companyService.getAll()).thenReturn(companies);

        mvc.perform(get(BASE_URL))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(companies)));
    }

    @Test
    void updateCompanySuccesfully() throws Exception {

        when(companyService.update(any(), any())).thenReturn(responseDTO);

        mvc.perform(put(String.format("%s/{companyId}", BASE_URL), ID).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateDTO)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(responseDTO)));
    }

    @Test
    void updateCompanyBadRequest() throws Exception {

        mvc.perform(put(String.format("%s/{companyId}", BASE_URL), ID))
            .andExpect(status().isBadRequest());

    }

    @Test
    void updateCompanyNotFound() throws Exception {
        when(companyService.update(any(), any())).thenThrow(new EntityNotFoundException(Company.class, ID));

        mvc.perform(put(String.format("%s/{companyId}", BASE_URL), ID).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateDTO)))
            .andExpect(status().isNotFound());
    }
}