package com.adasoft.tis.controllers.impl;

import com.adasoft.tis.controllers.PublicationRestController;
import com.adasoft.tis.domain.Publication;
import com.adasoft.tis.dto.publication.CreatePublicationDTO;
import com.adasoft.tis.dto.publication.PublicationResponseDTO;
import com.adasoft.tis.dto.publication.UpdatePublicationDTO;
import com.adasoft.tis.services.PublicationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@RestController
@RequestMapping("/publications")
@AllArgsConstructor
public class PublicationRestControllerImpl implements PublicationRestController {
    private PublicationService publicationService;

    @PostMapping
    @Override
    public ResponseEntity<PublicationResponseDTO> create(
        @RequestAttribute("userId") final Long userId,
        @Valid @RequestBody CreatePublicationDTO publicationDTO) {
        PublicationResponseDTO responseDTO = publicationService.create(publicationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{publicationId}")
    @Override
    public ResponseEntity<PublicationResponseDTO> update(
        @RequestAttribute("userId") final Long userId,
        @NotNull @PathVariable("publicationId") final Long id,
        @Valid @RequestBody final UpdatePublicationDTO publicationDTO) {
        PublicationResponseDTO responseDTO = publicationService.update(id, publicationDTO);

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{publicationId}")
    @Override
    public ResponseEntity<?> delete(
        @RequestAttribute("userId") final Long userId,
        @NotNull @PathVariable("publicationId") final Long id) {
        publicationService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Override
    public ResponseEntity<Collection<PublicationResponseDTO>> getByAdviserId(
        @RequestAttribute("userId") final Long userId,
        @NotNull @RequestParam("adviserId") final Long adviserId,
        @NotNull @RequestParam("type") final Publication.PublicationType type) {
        Collection<PublicationResponseDTO> publicationResponseDTOS = publicationService.getByAdviserId(adviserId, type);

        return ResponseEntity.ok(publicationResponseDTOS);
    }

    @GetMapping("/published")
    @Override
    public ResponseEntity<Collection<PublicationResponseDTO>> getByAdviserIdSemester(
        @NotNull @RequestParam("adviserId") final Long adviserId,
        @NotNull @RequestParam("type") final Publication.PublicationType type,
        @NotNull @RequestParam("semester") final String semester) {
        Collection<PublicationResponseDTO> publicationResponseDTOS = publicationService.getByAdviserIdSemester(adviserId, type, semester);

        return ResponseEntity.ok(publicationResponseDTOS);
    }
}
