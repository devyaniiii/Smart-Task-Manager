package com.example.taskmanager.service;

import com.example.taskmanager.model.Task;
import java.util.List;

public interface TaskService {
    List<Task> getTasks(String status);

    Task getTask(Long id);

    Task saveTask(Task task);

    void deleteTask(Long id);

    Task toggleTask(Long id);
}