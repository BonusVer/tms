package com.bonusver.task.client;

import com.bonusver.task.dto.TaskCommentRequestDto;
import com.bonusver.task.dto.TaskCommentResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TaskCommentClient {
    Flux<TaskCommentResponseDto> getCommentsForTask(Long taskId);
    Mono<TaskCommentRequestDto> createComment(Long taskId, Long authorId, String comment);
}
