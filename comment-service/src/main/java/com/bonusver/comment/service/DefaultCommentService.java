package com.bonusver.comment.service;

import com.bonusver.comment.dto.CreateCommentDto;
import com.bonusver.comment.dto.TaskCommentDto;
import com.bonusver.comment.entity.TaskComment;
import com.bonusver.comment.mapper.CommentMapper;
import com.bonusver.comment.repository.TaskCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultCommentService implements CommentService {

    private final TaskCommentRepository taskCommentRepository;
    private final CommentMapper commentMapper;


    @Override
    public Mono<TaskCommentDto> createComment(CreateCommentDto dto) {
        TaskComment comment = commentMapper.fromCreateDto(dto);
        comment.setId(UUID.randomUUID());
        return this.taskCommentRepository.save(comment)
                .map(commentMapper::toDto);
    }

    @Override
    public Flux<TaskCommentDto> findCommentsByTaskId(Long taskId) {
        return this.taskCommentRepository.findAllByTaskId(taskId)
                .map(this.commentMapper::toDto);
    }
}
