package com.bonusver.task.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreatedCommentDto(
    @NotNull(message = "{comment.create.comment_is_null}")
    @Size(min = 3, max = 1000, message = "{comment.create.comment_size_is_invalid}")
    String comment
) {
}
