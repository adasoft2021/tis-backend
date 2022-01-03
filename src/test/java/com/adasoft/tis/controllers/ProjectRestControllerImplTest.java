package com.adasoft.tis.controllers;

import com.adasoft.tis.controllers.impl.ProjectRestControllerImpl;
import com.adasoft.tis.core.utils.JWTProvider;
import com.adasoft.tis.dto.project.CreateProjectDTO;
import com.adasoft.tis.dto.project.ProjectResponseDTO;
import com.adasoft.tis.dto.publication.PublicationResponseDTO;
import com.adasoft.tis.services.ProjectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProjectRestControllerImpl.class)
public class ProjectRestControllerImplTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private JWTProvider jwtProvider;
    @MockBean
    private ProjectService projectService;

    private static final String BASE_URL = "/projects";
    private static final String X_TOKEN = "X-Token";
    private static final String TOKEN_VALUE = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MX0.fhc3wykrAnRpcKApKhXiahxaOe8PSHatad31NuIZ0Zg";
    private static final Long USER_ID = 1L;
    private static final Long ID = 1L;
    private static final String TITLE = "Proyecto";

    private static final Long IDP = 1996128482800373344L;
    private static final String TITLEP = "Nuevo Título para la convocatoria";
    private static final LocalDateTime DATE = LocalDateTime.now();
    private static final String CODE = "CPTIS-0609-2021";
    private static final String SEMESTER = "2-2021";
    private static final String FILE_URL = "https://drive.google.com/file/d/1Y1UxDtPa7Qr7uPzJ32D6pB7nwbCrrXxe/view?usp=sharing";

    private static final Long IDP2 = 1996128482500373344L;
    private static final String TITLEP2 = "Nuevo Título para la Hoja de especificaciones";
    private static final String CODE2 = "CPTIS";
    private static final String SEMESTER2 = "2-2021";
    private static final String FILE_URL2 = "https://drive.google.com/file/d/1Y1UxDtPa7Qr7uPzJ32D6pB7nwbCrrXxe/view?usp=sharing";

    private static final CreateProjectDTO createProjectDTO = new CreateProjectDTO();
    private static final ProjectResponseDTO responseDTO = new ProjectResponseDTO();
    private static final PublicationResponseDTO publication1 = new PublicationResponseDTO();
    private static final PublicationResponseDTO specificationSheet = new PublicationResponseDTO();

    @BeforeAll
    static void setup() {
        specificationSheet.setCode(CODE2);
        specificationSheet.setDate(DATE);
        specificationSheet.setId(IDP2);
        specificationSheet.setTitle(TITLEP2);
        specificationSheet.setSemester(SEMESTER2);
        specificationSheet.setFileUrl(FILE_URL2);
        specificationSheet.setCreatedAt(DATE);
        specificationSheet.setUpdatedAt(DATE);
        specificationSheet.setDeleted(false);

        publication1.setCode(CODE);
        publication1.setDate(DATE);
        publication1.setId(IDP);
        publication1.setTitle(TITLEP);
        publication1.setSemester(SEMESTER);
        publication1.setFileUrl(FILE_URL);
        publication1.setCreatedAt(DATE);
        publication1.setUpdatedAt(DATE);
        publication1.setDeleted(false);

        createProjectDTO.setCreatedAt(DATE);
        createProjectDTO.setTitle(TITLE);
        createProjectDTO.setUpdatedAt(DATE);
        createProjectDTO.setDeleted(false);
        createProjectDTO.setCreateannouncementId(IDP);
        createProjectDTO.setSpecificationSheetId(IDP2);

        responseDTO.setId(ID);
        responseDTO.setDeleted(false);
        responseDTO.setCreatedAt(DATE);
        responseDTO.setUpdatedAt(DATE);
        responseDTO.setTitle(TITLE);
        responseDTO.setAnnouncement(publication1);
        responseDTO.setSpecificationSheet(specificationSheet);
    }
    @Test
    void createPublicationSuccessfully() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        when(projectService.create(any(), any())).thenReturn(responseDTO);

        mvc.perform(post(BASE_URL).header(X_TOKEN, TOKEN_VALUE).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createProjectDTO)))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(responseDTO)));
    }
}
