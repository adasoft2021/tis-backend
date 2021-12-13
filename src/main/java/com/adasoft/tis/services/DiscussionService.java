package com.adasoft.tis.services;

import com.adasoft.tis.core.exceptions.EntityNotFoundException;
import com.adasoft.tis.domain.Adviser;
import com.adasoft.tis.domain.Company;
import com.adasoft.tis.domain.Discussion;
import com.adasoft.tis.domain.User;
import com.adasoft.tis.dto.discussion.CreateDiscussionDTO;
import com.adasoft.tis.dto.discussion.DiscussionResponseDTO;
import com.adasoft.tis.repository.DiscussionRepository;
import com.adasoft.tis.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static com.adasoft.tis.core.utils.Preconditions.checkArgument;

@AllArgsConstructor
@Service
public class DiscussionService {
    private UserRepository userRepository;
    private DiscussionRepository discussionRepository;
    private ModelMapper discussionMapper;

    public DiscussionResponseDTO create(final Long userId, final CreateDiscussionDTO dto) {
        checkArgument(userId != null, "El Id de User no puede ser nulo.");
        checkArgument(dto != null, "CreateDiscussionDTO no puede ser nulo.");
        Discussion defaultDiscussion = discussionMapper.map(dto, Discussion.class);
        User foundUser = userRepository.findById(userId).orElseThrow(
            () -> new EntityNotFoundException(User.class, dto.getCreatedById())
        );
        defaultDiscussion.setCreatedBy(foundUser);
        Set<User> participants = new HashSet<>();
        participants.add(foundUser);
        if (foundUser instanceof Adviser) {
            User company = userRepository.findById(dto.getCompanyId()).orElseThrow(
                () -> new EntityNotFoundException(User.class, dto.getCreatedById())
            );
            participants.add(company);
        } else if (foundUser instanceof Company) {
            participants.add(((Company) foundUser).getAdviser());
        }
        defaultDiscussion.setParticipants(participants);
        Discussion discussion = discussionRepository.save(defaultDiscussion);
        return discussionMapper.map(discussion, DiscussionResponseDTO.class);
    }
}
