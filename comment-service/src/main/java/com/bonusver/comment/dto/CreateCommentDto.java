package com.bonusver.comment.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateCommentDto(
        Long taskId,
        Long authorId,
        String comment
) {
}
