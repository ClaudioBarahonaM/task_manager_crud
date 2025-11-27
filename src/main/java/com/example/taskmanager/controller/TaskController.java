package com.example.taskmanager.controller;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskRepository;
import jakarta.persistence.Id;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tasks")
public class TaskController {

    private final TaskRepository repository;
    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }
    
    @GetMapping    
    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return repository.save(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody Task taskData) {
        return repository.findById(id)
                .map(task -> {
                    task.setTitle(taskData.getTitle());
                    task.setDescription(taskData.getDescription());
                    task.setCompleted(taskData.isCompleted());
                    repository.save(task);
                    return ResponseEntity.noContent().build();
                   }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        return repository.findById(id)
                .map(task ->  {
                    repository.delete(task);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
