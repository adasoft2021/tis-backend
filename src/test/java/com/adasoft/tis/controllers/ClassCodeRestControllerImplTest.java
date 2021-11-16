package com.adasoft.tis.controllers;

import com.adasoft.tis.controllers.impl.ClassCodeRestControllerImpl;
import com.adasoft.tis.core.exceptions.EntityNotFoundException;
import com.adasoft.tis.core.exceptions.ErrorResponse;
import com.adasoft.tis.core.utils.JWTProvider;
import com.adasoft.tis.domain.ClassCode;
import com.adasoft.tis.dto.classCode.CreateClassCodeDTO;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClassCodeRestControllerImpl.class)
class ClassCodeRestControllerImplTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private JWTProvider jwtProvider;
    @MockBean
    private ClassCodeService classCodeService;

    private static final String BASE_URL = "/class-codes";
    private static final String X_TOKEN = "X-Token";
    private static final String TOKEN_VALUE = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MX0.fhc3wykrAnRpcKApKhXiahxaOe8PSHatad31NuIZ0Zg";
    private static final Long USER_ID = 6430556344271188064L;

    private static final CreateClassCodeDTO CREATE_CLASS_CODE_DTO = new CreateClassCodeDTO();

    @BeforeAll
    static void setup() {
        CREATE_CLASS_CODE_DTO.setCode("asd-fgh-jkl");
    }

    @Test
    void validateClassCodeSuccess() throws Exception {
        when(classCodeService.validateClassCode(any())).thenReturn(null);

        mvc.perform(post(BASE_URL).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(CREATE_CLASS_CODE_DTO)))
            .andExpect(status().isNoContent());
    }

    @Test
    void validateClassCodeBadRequest() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        CreateClassCodeDTO classCodeDTO = new CreateClassCodeDTO();

        mvc.perform(post(BASE_URL).contentType(MediaType.APPLICATION_JSON).header(X_TOKEN, TOKEN_VALUE)
                .content(objectMapper.writeValueAsString(classCodeDTO)))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("title").value("Las validaciones de la entidad no han pasado."));
    }

    @Test
    void validateClassCodeNotFound() throws Exception {
        when(classCodeService.validateClassCode(any())).thenThrow(new EntityNotFoundException(ClassCode.class, 1) {
            @Override
            protected String getExceptionDetail() {
                String message = super.getExceptionDetail().replace("id", "código de clase");
                return message.replace("1", CREATE_CLASS_CODE_DTO.getCode());
            }
        });

        ErrorResponse errorResponse = ErrorResponse.builder()
            .title("No se pudo encontrar la entidad")
            .message(String.format(
                "ClassCode con código de clase %s no se pudo encontrar o no existe.",
                CREATE_CLASS_CODE_DTO.getCode()))
            .build();

        mvc.perform(post(BASE_URL).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(CREATE_CLASS_CODE_DTO)))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(errorResponse)));
    }
}