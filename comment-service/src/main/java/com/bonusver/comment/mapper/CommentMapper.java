package com.bonusver.comment.mapper;

import com.bonusver.comment.dto.CreateCommentDto;
import com.bonusver.comment.dto.TaskCommentDto;
import com.bonusver.comment.entity.TaskComment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "taskId", source = "taskId")
    @Mapping(target = "authorId", source = "authorId")
    @Mapping(target = "comment", source = "comment")
    TaskCommentDto toDto(TaskComment taskComment);

    TaskComment toEntity(TaskCommentDto taskCommentDto);

    @Mapping(target = "id", ignore = true)
    TaskComment fromCreateDto(CreateCommentDto createCommentDto);

    CreateCommentDto toCreateDto(TaskComment taskComment);
}
