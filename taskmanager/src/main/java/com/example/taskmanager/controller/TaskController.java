package com.example.taskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.taskmanager.service.TaskService;
import com.example.taskmanager.entity.Task;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public String getTasks(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();
        model.addAttribute("tasks", taskService.getTasksByUser(username));
        return "tasks";
    }

    @GetMapping("/add")
    public String showAddTaskForm(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("categories", taskService.getAllCategories());
        return "add_task";
    }

    @PostMapping("/add")
    public String addTask(@AuthenticationPrincipal UserDetails userDetails,
                          @ModelAttribute Task task,
                          @RequestParam(required = false) Long categoryId) {
        taskService.addTask(userDetails.getUsername(), task, categoryId);
        return "redirect:/home";
    }
}