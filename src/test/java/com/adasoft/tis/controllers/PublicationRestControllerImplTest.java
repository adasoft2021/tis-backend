package com.adasoft.tis.controllers;

import com.adasoft.tis.controllers.impl.PublicationRestControllerImpl;
import com.adasoft.tis.core.exceptions.EntityNotFoundException;
import com.adasoft.tis.core.exceptions.ErrorResponse;
import com.adasoft.tis.core.utils.JWTProvider;
import com.adasoft.tis.domain.Adviser;
import com.adasoft.tis.domain.Publication;
import com.adasoft.tis.dto.publication.CreatePublicationDTO;
import com.adasoft.tis.dto.publication.PublicationResponseDTO;
import com.adasoft.tis.dto.publication.UpdatePublicationDTO;
import com.adasoft.tis.services.PublicationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PublicationRestControllerImpl.class)
class PublicationRestControllerImplTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private JWTProvider jwtProvider;
    @MockBean
    private PublicationService publicationService;

    private static final String BASE_URL = "/publications";
    private static final String X_TOKEN = "X-Token";
    private static final String TOKEN_VALUE = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MX0.fhc3wykrAnRpcKApKhXiahxaOe8PSHatad31NuIZ0Zg";
    private static final Long USER_ID = 1996128482800373344L;

    private static final CreatePublicationDTO CREATE_PUBLICATION_DTO = new CreatePublicationDTO();
    private static final UpdatePublicationDTO UPDATE_PUBLICATION_DTO = new UpdatePublicationDTO();
    private static final PublicationResponseDTO PUBLICATION_RESPONSE_DTO = new PublicationResponseDTO();

    private static final Long ID = 1996128482800373344L;
    private static final String TITLE = "Nuevo Título para la publicación";
    private static final LocalDateTime DATE = LocalDateTime.now();
    private static final String CODE = "CPTIS-0609-2021";
    private static final String SEMESTER = "2-2021";
    private static final String FILE_URL = "https://drive.google.com/file/d/1Y1UxDtPa7Qr7uPzJ32D6pB7nwbCrrXxe/view?usp=sharing";

    @BeforeAll
    static void setup() {
        CREATE_PUBLICATION_DTO.setCode(CODE);
        CREATE_PUBLICATION_DTO.setCreatedById(ID);
        CREATE_PUBLICATION_DTO.setDate(DATE);
        CREATE_PUBLICATION_DTO.setSemester(SEMESTER);
        CREATE_PUBLICATION_DTO.setTitle(TITLE);
        CREATE_PUBLICATION_DTO.setFileUrl(FILE_URL);
        CREATE_PUBLICATION_DTO.setType(Publication.PublicationType.ANNOUNCEMENT);

        UPDATE_PUBLICATION_DTO.setId(ID);
        UPDATE_PUBLICATION_DTO.setUpdatedAt(DATE);
        UPDATE_PUBLICATION_DTO.setTitle(TITLE);
        UPDATE_PUBLICATION_DTO.setDate(DATE);
        UPDATE_PUBLICATION_DTO.setCode(CODE);
        UPDATE_PUBLICATION_DTO.setSemester(SEMESTER);
        UPDATE_PUBLICATION_DTO.setFileUrl(FILE_URL);

        PUBLICATION_RESPONSE_DTO.setId(ID);
        PUBLICATION_RESPONSE_DTO.setDeleted(false);
        PUBLICATION_RESPONSE_DTO.setCreatedAt(DATE);
        PUBLICATION_RESPONSE_DTO.setUpdatedAt(UPDATE_PUBLICATION_DTO.getUpdatedAt());
        PUBLICATION_RESPONSE_DTO.setTitle(TITLE);
        PUBLICATION_RESPONSE_DTO.setDate(DATE);
        PUBLICATION_RESPONSE_DTO.setCode(CODE);
        PUBLICATION_RESPONSE_DTO.setSemester(SEMESTER);
        PUBLICATION_RESPONSE_DTO.setFileUrl(FILE_URL);
    }

    @Test
    void createPublicationSuccessfully() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        when(publicationService.create(any())).thenReturn(PUBLICATION_RESPONSE_DTO);

        mvc.perform(post(BASE_URL).header(X_TOKEN, TOKEN_VALUE).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(CREATE_PUBLICATION_DTO)))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(PUBLICATION_RESPONSE_DTO)));
    }

    @Test
    void createPublicationBadRequest() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        CreatePublicationDTO createPublicationDTO = new CreatePublicationDTO();
        mvc.perform(post(BASE_URL).header(X_TOKEN, TOKEN_VALUE).contentType(MediaType.APPLICATION_JSON)

                .content(objectMapper.writeValueAsString(createPublicationDTO)))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("title").value("Las validaciones de la entidad no han pasado."));
    }

    @Test
    void createPublicationNotFound() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        when(publicationService.create(any())).thenThrow(new EntityNotFoundException(Adviser.class, ID));

        ErrorResponse errorResponse = ErrorResponse.builder()
            .title("No se pudo encontrar la entidad")
            .message(String.format("Adviser con id %d no se pudo encontrar o no existe.", ID))
            .build();

        mvc.perform(post(BASE_URL).header(X_TOKEN, TOKEN_VALUE).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(CREATE_PUBLICATION_DTO)))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(errorResponse)));
    }

    @Test
    void updatePublicationSuccessfully() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        when(publicationService.update(any(), any(), any())).thenReturn(PUBLICATION_RESPONSE_DTO);
        mvc.perform(put(String.format("%s/{publicationId}", BASE_URL), ID)
                .header(X_TOKEN, TOKEN_VALUE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(UPDATE_PUBLICATION_DTO)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(PUBLICATION_RESPONSE_DTO)));
    }

    @Test
    void updatePublicationBadRequest() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        UpdatePublicationDTO updatePublicationDTO = new UpdatePublicationDTO();
        mvc.perform(put(String.format("%s/{publicationId}", BASE_URL), ID)
                .header(X_TOKEN, TOKEN_VALUE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatePublicationDTO)))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("title").value("Las validaciones de la entidad no han pasado."));
    }

    @Test
    void updatePublicationNotFound() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        when(publicationService.update(any(), any(), any())).thenThrow(new EntityNotFoundException(Publication.class, ID));

        ErrorResponse errorResponse = ErrorResponse.builder()
            .title("No se pudo encontrar la entidad")
            .message(String.format("Publication con id %d no se pudo encontrar o no existe.", ID))
            .build();

        mvc.perform(put(String.format("%s/{publicationId}", BASE_URL), ID)
                .header(X_TOKEN, TOKEN_VALUE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(UPDATE_PUBLICATION_DTO)))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(errorResponse)));
    }

    @Test
    void deletePublicationSuccessfully() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        mvc.perform(delete(String.format("%s/{publicationId}", BASE_URL), ID).header(X_TOKEN, TOKEN_VALUE))
            .andExpect(status().isNoContent());
    }

    @Test
    void deletePublicationNotFound() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        when(publicationService.delete(any(), any())).thenThrow(new EntityNotFoundException(Publication.class, ID));

        ErrorResponse errorResponse = ErrorResponse.builder()
            .title("No se pudo encontrar la entidad")
            .message(String.format("Publication con id %d no se pudo encontrar o no existe.", ID))
            .build();

        mvc.perform(delete(String.format("%s/{publicationId}", BASE_URL), ID).header(X_TOKEN, TOKEN_VALUE))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(errorResponse)));
    }

    @Test
    void getByAdviserIdSuccessfully() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        Collection<PublicationResponseDTO> publications = new HashSet<>();
        publications.add(PUBLICATION_RESPONSE_DTO);
        when(publicationService.getByAdviserId(any(), any())).thenReturn(publications);

        mvc.perform(get(BASE_URL)
                .queryParam("adviserId", String.valueOf(ID))
                .queryParam("type", Publication.PublicationType.ANNOUNCEMENT.toString())
                .header(X_TOKEN, TOKEN_VALUE))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(publications)));
    }

    @Test
    void getByAdviserIdNotFound() throws Exception {
        when(jwtProvider.decryptUserId(any())).thenReturn(USER_ID);
        when(publicationService.getByAdviserId(any(), any())).thenThrow(new EntityNotFoundException(Adviser.class, ID));
        ErrorResponse errorResponse = ErrorResponse.builder()
            .title("No se pudo encontrar la entidad")
            .message(String.format("Adviser con id %d no se pudo encontrar o no existe.", ID))
            .build();

        mvc.perform(get(BASE_URL)
                .queryParam("adviserId", String.valueOf(ID))
                .queryParam("type", Publication.PublicationType.ANNOUNCEMENT.toString())
                .header(X_TOKEN, TOKEN_VALUE))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(errorResponse)));
    }

    @Test
    void getByAdviserIdSemesterSuccessfully() throws Exception {
        Collection<PublicationResponseDTO> publications = new HashSet<>();
        publications.add(PUBLICATION_RESPONSE_DTO);
        when(publicationService.getByAdviserIdSemester(any(), any(), any())).thenReturn(publications);
        mvc.perform(get(BASE_URL + "/published")
                .queryParam("adviserId", String.valueOf(ID))
                .queryParam("type", Publication.PublicationType.ANNOUNCEMENT.toString())
                .queryParam("semester", SEMESTER))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(publications)));
    }

    @Test
    void getByAdviserIdSemesterBadRequest() throws Exception {

        mvc.perform(get(BASE_URL + "/published")
                .queryParam("adviserId", String.valueOf(ID))
                .queryParam("type", Publication.PublicationType.ANNOUNCEMENT.toString()))
            .andExpect(status().isBadRequest());
    }

    @Test
    void getByAdviserIdSemesterNotFound() throws Exception {
        when(publicationService.getByAdviserIdSemester(any(), any(), any()))
            .thenThrow(new EntityNotFoundException(Adviser.class, ID));
        ErrorResponse errorResponse = ErrorResponse.builder()
            .title("No se pudo encontrar la entidad")
            .message(String.format("Adviser con id %d no se pudo encontrar o no existe.", ID))
            .build();
        mvc.perform(get(BASE_URL + "/published")
                .queryParam("adviserId", String.valueOf(ID))
                .queryParam("semester", SEMESTER)
                .queryParam("type", Publication.PublicationType.ANNOUNCEMENT.toString()))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(errorResponse)));
    }

}