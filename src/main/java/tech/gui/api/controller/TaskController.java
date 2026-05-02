package tech.gui.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.gui.api.entity.Task;
import tech.gui.api.service.TaskService;
import tech.gui.api.dto.TaskDTO;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import tech.gui.api.dto.PriorityDTO;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @Operation(summary = "Lista tarefas com paginação e ordenação")
    @GetMapping
    public Page<TaskDTO> list(@PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return service.list(pageable);
    }
    @Operation(summary = "Buscar tarefa por ID")
    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(new TaskDTO(service.getById(id)));
    }

    @Operation(summary = "Cria uma tarefa")
    @PostMapping
    public ResponseEntity<TaskDTO> create(@Valid @RequestBody TaskDTO dto) {
        return ResponseEntity.ok(new TaskDTO(service.create(dto)));
    }

    @Operation(summary = "Atualiza uma tarefa")
    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> update(@PathVariable Long id, @Valid @RequestBody TaskDTO dto) {
        return ResponseEntity.ok(new TaskDTO(service.update(id, dto)));
    }

    @Operation(summary = "Marca tarefa como completa")
    @PatchMapping("/{id}/complete")
    public ResponseEntity<TaskDTO> complete(@PathVariable Long id) {
        return ResponseEntity.ok(new TaskDTO(service.markAsCompleted(id)));
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


    @Operation(summary = "Atualiza prioridade da tarefa")
    @PatchMapping("/{id}/priority")
    public ResponseEntity<TaskDTO> updatePriority(
            @PathVariable Long id,
            @RequestBody PriorityDTO dto) {

        Task updated = service.updatePriority(id, dto.getPriority());

        return ResponseEntity.ok(new TaskDTO(updated));
    }

}
