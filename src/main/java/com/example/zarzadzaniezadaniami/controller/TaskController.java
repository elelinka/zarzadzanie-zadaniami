package com.example.zarzadzaniezadaniami.controller;

import com.example.zarzadzaniezadaniami.entity.Task;
import com.example.zarzadzaniezadaniami.entity.category.TaskCategory;
import com.example.zarzadzaniezadaniami.entity.status.TaskStatus;
import com.example.zarzadzaniezadaniami.repository.TaskRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TaskController {

    private TaskRepository repository;
    private List<Task> tasks;

    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/tasks")
    public String getAll(@RequestParam TaskStatus status, Model model) {
        tasks = repository.getByStatus(status);

        model.addAttribute("tasks", tasks);
        repository.close();
        return "tasks";
    }

    @PostMapping("/add")
    public String add(@RequestParam String title, @RequestParam(required = false) String description,
                      @RequestParam TaskCategory category, @RequestParam TaskStatus status) {
        Task task;

        if (!(isParamEmpty(title, category, status))) {
            if (StringUtils.isEmpty(description)) {
                task = new Task(title, category, status);
                repository.save(task);
                return "redirect:/success";
            } else {
                task = new Task(title, category, status);
                repository.save(task);
                return "redirect:/success";
            }
        }
        return "redirect:/err";
    }

    private boolean isParamEmpty(String title, TaskCategory category, TaskStatus status) {
        return StringUtils.isEmpty(title) || StringUtils.isEmpty(category) || StringUtils.isEmpty(status);

    }

    @GetMapping("/success")
    public String successPage() {
        return "success.html";
    }

    @GetMapping("/err")
    public String errPage() {
        return "err.html";
    }
}
