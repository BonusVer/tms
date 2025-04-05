package com.bonusver.task.dto;

import com.bonusver.task.entity.Task;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateTaskDto {
    @NotNull(message = "{task.create.title_is_null}")
    @Size(min = 3, max = 50, message = "{task.create.title_size_is_invalid}")
    private String title;
    @NotNull(message = "{task.create.details_is_null}")
    private String details;
    private Task.Priority priority;
    private Task.Status status;
    //Временно, потом будет тут удален, т. к. автор будет браться из токена
    private Long authorId;
    private Long executorId;
}
