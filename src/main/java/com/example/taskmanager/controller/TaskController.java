package com.example.taskmanager.controller;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping({ "/", "/tasks" })
    public String index(@RequestParam(defaultValue = "all") String status, Model model) {
        model.addAttribute("tasks", service.getTasks(status));
        model.addAttribute("status", status);
        model.addAttribute("task", new Task());
        return "index";
    }

    @GetMapping("/tasks/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("task", service.getTask(id));
        return "edit";
    }

    @PostMapping("/tasks/save")
    public String save(@Valid @ModelAttribute Task task) {
        service.saveTask(task);
        return "redirect:/tasks";
    }

    @PostMapping("/tasks/{id}/toggle")
    public String toggle(@PathVariable Long id) {
        service.toggleTask(id);
        return "redirect:/tasks";
    }

    @PostMapping("/tasks/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.deleteTask(id);
        return "redirect:/tasks";
    }
}