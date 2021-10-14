package com.adasoft.tis.controllers.impl;

import com.adasoft.tis.controllers.PublicationRestController;
import com.adasoft.tis.domain.Publication;
import com.adasoft.tis.dto.publication.PublicationResponseDTO;
import com.adasoft.tis.dto.publication.UpdatePublicationDTO;
import com.adasoft.tis.services.PublicationService;
import lombok.AllArgsConstructor;
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

    @PutMapping("/{publicationId}")
    @Override
    public ResponseEntity<PublicationResponseDTO> update(
        @NotNull @PathVariable("publicationId") final Long id,
        @Valid @RequestBody final UpdatePublicationDTO publicationDTO) {
        PublicationResponseDTO responseDTO = publicationService.update(id, publicationDTO);

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{publicationId}")
    @Override
    public ResponseEntity<?> delete(@NotNull @PathVariable("publicationId") final Long id) {
        publicationService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Override
    public ResponseEntity<Collection<PublicationResponseDTO>> getByAdviserId(
        @NotNull @RequestParam("adviserId") final Long adviserId,
        @NotNull @RequestParam("type") final Publication.PublicationType type) {
        Collection<PublicationResponseDTO> publicationResponseDTOS = publicationService.getByAdviserId(adviserId, type);

        return ResponseEntity.ok(publicationResponseDTOS);
    }
}
