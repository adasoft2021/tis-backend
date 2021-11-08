package com.adasoft.tis.controllers;

import com.adasoft.tis.controllers.impl.SemesterRestControllerImpl;
import com.adasoft.tis.core.utils.JWTProvider;
import com.adasoft.tis.dto.semester.SemesterResponseDTO;
import com.adasoft.tis.services.SemesterService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SemesterRestControllerImpl.class)
class SemesterRestControllerImplTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private JWTProvider jwtProvider;
    @MockBean
    private SemesterService semesterService;

    private static final String BASE_URL = "/semesters";
    private static final String SEMESTER = "2-2021";
    private static SemesterResponseDTO responseDTO;

    @BeforeAll
    static void setup() {
        responseDTO = new SemesterResponseDTO();
        responseDTO.setSemester(SEMESTER);
    }

    @Test
    void getSemesterSuccessfully() throws Exception {

        mvc.perform(get(String.format("%s/now", BASE_URL)))
            .andExpect(status().isOk());
    }
}