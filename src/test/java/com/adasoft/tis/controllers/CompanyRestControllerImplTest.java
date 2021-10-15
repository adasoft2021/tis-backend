package com.adasoft.tis.controllers;

import com.adasoft.tis.controllers.impl.CompanyRestControllerImpl;
import com.adasoft.tis.dto.company.CompanyResponseDTO;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    private static final String SHORT = "ADASOFT";
    private static final String NAME = "ÄDASOFTWARE";
    private static final String COMPANY_TYPE = "SRL";
    private static final String ADDRESS = "Jordan y Oquendo";
    private static final String EMAIL = "adasoftsrl@gmail.com";
    private static final String[] PARTNERS = {"Violeta Guzman", "Jesus Jimenez", "Leonardo Roldan", "Luis Tapia", "Viviana Tolaba"};
    private static CompanyResponseDTO responseDTO;

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

}