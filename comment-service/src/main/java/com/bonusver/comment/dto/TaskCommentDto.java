package com.bonusver.comment.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record TaskCommentDto(
        UUID id,
        Long taskId,
        Long authorId,
        String comment
) {
}
