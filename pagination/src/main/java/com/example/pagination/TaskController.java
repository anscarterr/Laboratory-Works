package com.example.pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Поиск по имени
    @GetMapping("/search")
    public List<Task> searchTasks(@RequestParam String name) {
        return taskService.searchTasksByName(name);
    }

    // Пагинация
    @GetMapping
    public Page<Task> getTasksWithPagination(@RequestParam int page, @RequestParam int size) {
        return taskService.getTasksWithPagination(PageRequest.of(page, size));
    }

    // Создание новой задачи
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }
}

