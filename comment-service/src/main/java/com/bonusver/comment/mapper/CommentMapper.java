package com.bonusver.comment.mapper;

import com.bonusver.comment.dto.TaskCommentDto;
import com.bonusver.comment.entity.TaskComment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    TaskCommentDto toDto(TaskComment taskComment);
    TaskComment toEntity(TaskCommentDto taskCommentDto);
}
