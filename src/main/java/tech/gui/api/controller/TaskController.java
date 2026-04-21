package tech.gui.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.gui.api.entity.Task;
import tech.gui.api.service.TaskService;
import tech.gui.api.dto.TaskDTO;
import jakarta.validation.Valid;

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

    @GetMapping("/{id}")
    public ResponseEntity<Task> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<Task> create(@Valid @RequestBody TaskDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable Long id, @Valid @RequestBody TaskDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<Task> complete(@PathVariable Long id) {
        return ResponseEntity.ok(service.markAsCompleted(id));
    }

    @DeleteMapping
    public ResponseEntity<Void> clearTasks(){
        service.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Tarefa excluída com sucesso");
    }
}
