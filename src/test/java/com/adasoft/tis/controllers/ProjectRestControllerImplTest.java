package com.adasoft.tis.controllers;

import com.adasoft.tis.controllers.impl.ProjectRestControllerImpl;
import com.adasoft.tis.core.utils.JWTProvider;
import com.adasoft.tis.dto.project.ProjectResponseDTO;
import com.adasoft.tis.dto.proposal.ProposalResponseDTO;
import com.adasoft.tis.services.CompanyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;

@WebMvcTest(ProjectRestControllerImpl.class)
public class ProjectRestControllerImplTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private JWTProvider jwtProvider;
    @MockBean
    private CompanyService companyService;

    private static final String BASE_URL = "/projects";
    private static final String X_TOKEN = "X-Token";
    private static final String TOKEN_VALUE = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MX0.fhc3wykrAnRpcKApKhXiahxaOe8PSHatad31NuIZ0Zg";
    private static final Long USER_ID = 1L;
    private static final Long ID = 1L;
    private static final String TITLE = "Proyecto";
    private static final String COMPANY_TYPE = "SRL";
    private static final String ADDRESS = "Jordan y Oquendo";
    private static final String EMAIL = "acme@gmail.com";
    private static final String TELEPHONE = "77777777";
    private static final String[] PARTNERS = {"Violeta Guzman", "Jesus Jimenez", "Leonardo Roldan", "Luis Tapia", "Viviana Tolaba"};

    private static final Long IDP = 1996128482800373344L;
    private static final String TITLEP = "Nuevo Título para la publicación";
    private static final LocalDateTime DATE = LocalDateTime.now();
    private static final String CODE = "CPTIS-0609-2021";
    private static final String SEMESTER = "2-2021";
    private static final String FILE_URL = "https://drive.google.com/file/d/1Y1UxDtPa7Qr7uPzJ32D6pB7nwbCrrXxe/view?usp=sharing";

    private static ProjectResponseDTO responseDTO;

    @BeforeAll
    static void setup() {
        responseDTO = new ProjectResponseDTO();
        responseDTO.setId(ID);
    }

}
