package com.bonusver.comment.repository;

import com.bonusver.comment.entity.TaskComment;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface TaskCommentRepository  extends ReactiveCrudRepository<TaskComment, UUID> {

    Flux<TaskComment> findAllByTaskId(Long taskId);
}
