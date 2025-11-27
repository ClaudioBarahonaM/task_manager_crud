package com.example.taskmanager.service;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> findAll() {
        return repository.findAll();
    }

    public Task create(Task task) {
        return repository.save(task);
    }

    public Optional<Task> findById(Long id) {
        return repository.findById(id);
    }

    public Task update(Task task, Task data) {
        task.setTitle(data.getTitle());
        task.setDescription(data.getDescription());
        task.setCompleted(data.isCompleted());
        return repository.save(task);
    }

    public void delete(Task task) {
        repository.delete(task);
    }
}
