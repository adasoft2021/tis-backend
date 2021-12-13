package com.adasoft.tis.services;

import com.adasoft.tis.core.exceptions.DefaultTisDomainException;
import com.adasoft.tis.core.exceptions.EntityNotFoundException;
import com.adasoft.tis.domain.Comment;
import com.adasoft.tis.domain.Discussion;
import com.adasoft.tis.domain.User;
import com.adasoft.tis.dto.comment.CommentResponseDTO;
import com.adasoft.tis.dto.comment.CreateCommentDTO;
import com.adasoft.tis.repository.CommentRepository;
import com.adasoft.tis.repository.DiscussionRepository;
import com.adasoft.tis.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CommentService {
    private DiscussionRepository discussionRepository;
    private ModelMapper commentMapper;
    private UserRepository userRepository;
    private CommentRepository commentRepository;

    public CommentResponseDTO create(final Long discussionId, final CreateCommentDTO dto) {
        Discussion foundDiscussion = discussionRepository.findById(discussionId)
            .orElseThrow(() -> new EntityNotFoundException(Discussion.class, discussionId));
        User foundUser = userRepository.findById(dto.getCreatedById())
            .orElseThrow(() -> new EntityNotFoundException(Comment.class, dto.getCreatedById()));
        if (!foundDiscussion.getParticipants().contains(foundUser))
            throw new DefaultTisDomainException(HttpStatus.METHOD_NOT_ALLOWED,
                "Participante con id " + foundUser.getId() + " no registrado");
        Comment defaultComment = commentMapper.map(dto, Comment.class);
        defaultComment.setCreatedBy(foundUser);
        defaultComment.setDiscussion(foundDiscussion);
        Comment savedComment = commentRepository.save(defaultComment);
        return commentMapper.map(savedComment, CommentResponseDTO.class);
    }
}
