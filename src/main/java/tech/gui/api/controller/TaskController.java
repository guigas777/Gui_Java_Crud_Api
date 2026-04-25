package tech.gui.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.gui.api.entity.Task;
import tech.gui.api.service.TaskService;
import tech.gui.api.dto.TaskDTO;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @Operation(summary = "Lista todas as tarefas")
    @GetMapping
    public List<Task> listTasks() {
        return service.listAll();
    }

    @Operation(summary = "Buscar tarefa por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Task> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Operation(summary = "Cria uma tarefa")
    @PostMapping
    public ResponseEntity<Task> create(@Valid @RequestBody TaskDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @Operation(summary = "Atualiza uma tarefa")
    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable Long id, @Valid @RequestBody TaskDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @Operation(summary = "Marca tarefa como completa")
    @PatchMapping("/{id}/complete")
    public ResponseEntity<Task> complete(@PathVariable Long id) {
        return ResponseEntity.ok(service.markAsCompleted(id));
    }

    @Operation(summary = "Deleta todas as tarefas")
    @DeleteMapping
    public ResponseEntity<Void> clearTasks(){
        service.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Deleta tarefa por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Tarefa excluída com sucesso");
    }
}
