package tech.gui.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.gui.api.entity.Task;
import tech.gui.api.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public List<Task> listTasks() {
        return service.listAll();
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task){
        return ResponseEntity.ok(service.create(task));
    }

    @DeleteMapping
    public ResponseEntity<Void> clearTasks(){
        service.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
