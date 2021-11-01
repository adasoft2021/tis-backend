package com.adasoft.tis.controllers;

import com.adasoft.tis.controllers.impl.AdviserRestControllerImpl;
import com.adasoft.tis.core.exceptions.EntityNotFoundException;
import com.adasoft.tis.core.exceptions.ErrorResponse;
import com.adasoft.tis.domain.Adviser;
import com.adasoft.tis.dto.classCode.ClassCodeResponseDTO;
import com.adasoft.tis.services.AdviserService;
import com.adasoft.tis.services.ClassCodeService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdviserRestControllerImpl.class)
class AdviserRestControllerImplTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AdviserService adviserService;
    @MockBean
    private ClassCodeService classCodeService;

    private static final String BASE_URL = "/advisers";
    private static final Long ID = 1L;
    private static final String CODE = "asd-asd-asd";
    private static final String TOKEN = "asdfghjk";


    private static ClassCodeResponseDTO classCodeDTO;

    @BeforeAll
    static void setup() {
        classCodeDTO = new ClassCodeResponseDTO();
        classCodeDTO.setCode(CODE);
    }

    @Test
    void createClassCodeSuccessfully() throws Exception {
        when(classCodeService.create(any())).thenReturn(classCodeDTO);

        mvc.perform(post(String.format("%s/{adviserId}/class-code", BASE_URL), ID).header("auth", TOKEN))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(classCodeDTO)));
    }

    @Test
    void createClassCodeAdviserNotFound() throws Exception {
        when(classCodeService.create(any())).thenThrow(new EntityNotFoundException(Adviser.class, ID));

        ErrorResponse errorResponse = ErrorResponse.builder()
            .title("No se pudo encontrar la entidad")
            .message(String.format("Adviser con id %d no se pudo encontrar o no existe.", ID))
            .build();

        mvc.perform(post(String.format("%s/{adviserId}/class-code", BASE_URL), ID).header("auth", TOKEN))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(errorResponse)));
    }

    @Test
    void createClassCodeUnauthorized() throws Exception {


        ErrorResponse errorResponse = ErrorResponse.builder()
            .title("El proceso no puede continuar")
            .message("Falta autorizacion")
            .build();

        mvc.perform(post(String.format("%s/{adviserId}/class-code", BASE_URL), ID).header("auth", ""))
            .andExpect(status().isUnauthorized()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(errorResponse)));
    }
}