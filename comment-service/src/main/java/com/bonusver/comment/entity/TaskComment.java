package com.bonusver.comment.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("task_comments")
public class TaskComment {
    @Id
    private UUID id;
    private Long taskId;
    private Long authorId;
    private String comment;
}
