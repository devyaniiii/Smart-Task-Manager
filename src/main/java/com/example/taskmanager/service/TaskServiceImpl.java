package com.example.taskmanager.service;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repo;

    public TaskServiceImpl(TaskRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Task> getTasks(String status) {
        if ("completed".equalsIgnoreCase(status))
            return repo.findByCompleted(true);
        if ("pending".equalsIgnoreCase(status))
            return repo.findByCompleted(false);
        return repo.findAll();
    }

    @Override
    public Task getTask(Long id) {
        return repo.findById(id).orElseThrow();
    }

    @Override
    public Task saveTask(Task task) {
        return repo.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Task toggleTask(Long id) {
        Task task = getTask(id);
        task.setCompleted(!task.isCompleted());
        return repo.save(task);
    }
}