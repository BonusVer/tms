package com.bonusver.task.service;

import ch.qos.logback.core.joran.conditional.IfAction;
import com.bonusver.task.controller.exceptions.NotAuthorExecutorException;
import com.bonusver.task.controller.exceptions.ResourceNotFoundException;
import com.bonusver.task.dto.CreateTaskDto;
import com.bonusver.task.dto.TaskDto;
import com.bonusver.task.entity.Task;
import com.bonusver.task.entity.User;
import com.bonusver.task.mapper.TaskMapper;
import com.bonusver.task.repository.TaskRepository;
import com.bonusver.task.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;


import java.util.List;


@Service
@AllArgsConstructor
public class DefaultTaskService implements TaskService{

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;

    @Override
    public List<TaskDto> findAllTasks() {
       List<Task> allTasks =  this.taskRepository.findAll();
       return  allTasks.stream().map(taskMapper::toTaskDto).toList();

    }

    @Override
    public List<TaskDto> findAllTasksByAuthorId(Long authorId) {
        if(!this.userRepository.existsById(authorId)) {
            throw new ResourceNotFoundException("User with id " + authorId + " not found");
        }
        List<Task> tasksByAuthorId = this.taskRepository.findByAuthorId(authorId);

        if(tasksByAuthorId.isEmpty()) {
            throw new ResourceNotFoundException("No tasks found for author with id " + authorId);
        }
        return tasksByAuthorId.stream().map(taskMapper::toTaskDto).toList();
    }

    @Override
    public List<TaskDto> findAllTasksByExecutorId(Long executorId) {
        if(!this.userRepository.existsById(executorId)) {
            throw new ResourceNotFoundException("User with id " + executorId + " not found");
        }
        List<Task> tasksByExecutorId = this.taskRepository.findByExecutorId(executorId);

        if(tasksByExecutorId.isEmpty()) {
            throw new ResourceNotFoundException("No tasks found for executor with id " + executorId);
        }
        return tasksByExecutorId.stream().map(taskMapper::toTaskDto).toList();
    }


    @Override
    @Transactional
    public CreateTaskDto saveTask(CreateTaskDto request, String currentUser) {
        //Данный код будет потом преобразован данными из токена
        User author = this.userRepository.findByEmail(currentUser)
                .orElseThrow(()-> new ResourceNotFoundException("Author Not Found with email: " + currentUser));
        User executor = this.userRepository.findByEmail(request.getExecutorEmail())
                .orElseThrow(()-> new ResourceNotFoundException("Executor Not Found with email: " + request.getExecutorEmail()));

        Task newTask = new Task();
        newTask.setTitle(request.getTitle());
        newTask.setDetails(request.getDetails());
        newTask.setPriority(Task.Priority.valueOf(request.getPriority()));
        newTask.setStatus(Task.Status.valueOf(request.getStatus()));
        newTask.setAuthor(author);
        newTask.setExecutor(executor);

        Task savedTask = this.taskRepository.save(newTask);
        return this.taskMapper.toCreateTaskDto(savedTask);
    }

    @Override
    @Transactional
    public void updateTask(Long taskId, TaskDto request, String currentUser) {

        Task task = this.taskRepository.findById(taskId)
                .orElseThrow(()-> new ResourceNotFoundException("Task Not Found with id: " + taskId));

        boolean isAuthor = currentUser.equals(task.getAuthor().getEmail());
        boolean isExecutor = currentUser.equals(task.getExecutor().getEmail());

        if (!isAuthor && !isExecutor) {
            throw new NotAuthorExecutorException("Only author or executor can update task");
        }

        if (!isAuthor && !isStatusOnlyUpdate(request)) {
            throw new NotAuthorExecutorException("Only author can update fields other than status");
        }


        if (request.getTitle() != null)
            task.setTitle(request.getTitle());
        if (request.getDetails() != null)
            task.setDetails(request.getDetails());
        if (request.getPriority() != null)
            task.setPriority(Task.Priority.valueOf(request.getPriority()));
        if (request.getStatus() != null)
            task.setStatus(Task.Status.valueOf(request.getStatus()));
        if (request.getExecutorEmail() != null) {
            User executor = this.userRepository.findByEmail(request.getExecutorEmail())
                    .orElseThrow(() -> new ResourceNotFoundException("Executor Not Found with email: " + request.getExecutorEmail()));
            task.setExecutor(executor);
        }
    }

    @Override
    @Transactional
    public void deleteTask(Long taskId, String currentUser) {

        Task task = this.taskRepository.findById(taskId)
                .orElseThrow(()-> new ResourceNotFoundException("Task Not Found with id: " + taskId));

        if (currentUser.equals(task.getAuthor().getEmail())) {
            this.taskRepository.deleteById(taskId);
        } else {
            throw new NotAuthorExecutorException("Only author can delete task");
        }
    }

    private boolean isStatusOnlyUpdate(TaskDto request) {
        return request.getTitle() == null
                && request.getDetails() == null
                && request.getPriority() == null
                && request.getExecutorEmail() == null
                && request.getStatus() != null;
    }
}
