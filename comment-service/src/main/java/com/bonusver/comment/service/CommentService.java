package com.bonusver.comment.service;

import com.bonusver.comment.entity.TaskComment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CommentService {
    Mono<TaskComment> createComment(Long taskId,Long authorId, String comment);
    Flux<TaskComment> findCommentsByTaskId(Long taskId);
}
