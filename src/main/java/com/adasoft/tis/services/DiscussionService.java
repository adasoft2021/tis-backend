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
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.Set;

public class DiscussionService {
    private UserRepository userRepository;
    private DiscussionRepository discussionRepository;
    private ModelMapper discussionMapper;

    public DiscussionResponseDTO create(final Long userId, final CreateDiscussionDTO dto) {
        Discussion defaultDiscussion = discussionMapper.map(dto, Discussion.class);
        User foundUser = userRepository.findById(userId).orElseThrow(
            () -> new EntityNotFoundException(User.class, dto.getCompanyId())
        );
        defaultDiscussion.setCreatedBy(foundUser);
        Set<User> participants = new HashSet<>();
        participants.add(foundUser);
        if (foundUser instanceof Adviser) {
            User company = userRepository.findById(dto.getCompanyId()).orElseThrow(
                () -> new EntityNotFoundException(User.class, dto.getCompanyId())
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
