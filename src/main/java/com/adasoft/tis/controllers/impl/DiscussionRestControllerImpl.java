package com.adasoft.tis.controllers.impl;

import com.adasoft.tis.controllers.DiscussionRestController;
import com.adasoft.tis.dto.discussion.CreateDiscussionDTO;
import com.adasoft.tis.dto.discussion.DiscussionResponseDTO;
import com.adasoft.tis.services.DiscussionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/discussions")
@AllArgsConstructor
public class DiscussionRestControllerImpl implements DiscussionRestController {
    private DiscussionService discussionService;

    @PostMapping
    @Override
    public ResponseEntity<DiscussionResponseDTO> create(@RequestAttribute Long userId,
                                                        @RequestBody CreateDiscussionDTO dto) {
        DiscussionResponseDTO responseDTO = discussionService.create(userId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}
