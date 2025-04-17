package com.bonusver.task.dto;

import com.bonusver.task.entity.Task;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskDto {

    private Long id;

    @NotNull(message = "{task.create.title_is_null}")
    @Size(min = 3, max = 50, message = "{task.create.title_size_is_invalid}")
    private String title;

    @NotNull(message = "{task.create.details_is_null}")
    private String details;

    @NotNull(message = "{task.create.priority_is_null}")
    @Pattern(regexp = "LOW|MEDIUM|HIGH",
            message = "{task.create.priority_is_invalid}")
    private String priority;

    @NotNull(message = "{task.create.status_is_null}")
    @Pattern(regexp = "WAITING|IN_PROGRESS|COMPLETED",
            message = "{task.create.status_is_invalid}")
    private String status;

    @NotNull(message = "{task.create.email_executor_is_null}")
    private String executorEmail;
}
