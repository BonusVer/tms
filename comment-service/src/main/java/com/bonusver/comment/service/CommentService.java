package com.bonusver.comment.service;

import com.bonusver.comment.dto.CreateCommentDto;
import com.bonusver.comment.dto.TaskCommentDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CommentService {
    Mono<TaskCommentDto> createComment(CreateCommentDto dto);
    Flux<TaskCommentDto> findCommentsByTaskId(Long taskId);
}
