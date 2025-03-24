package com.bonusver.task.controller;

import com.bonusver.task.entity.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    @GetMapping
    public List<Task> getAllTasks() {
        return
    }


}
