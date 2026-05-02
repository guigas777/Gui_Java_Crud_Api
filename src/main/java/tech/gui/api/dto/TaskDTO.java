package tech.gui.api.dto;

import jakarta.validation.constraints.NotBlank;
import tech.gui.api.entity.Task;
import tech.gui.api.entity.Priority;

public class TaskDTO {

    private Long id;

    @NotBlank(message = "Descrição é obrigatória")
    private String description;

    private Boolean completed;
    private Priority priority;

    public TaskDTO() {
    }

    public TaskDTO(Task task) {
        this.id = task.getId();
        this.description = task.getDescription();
        this.completed = task.getCompleted();
        this.priority = task.getPriority();
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}


