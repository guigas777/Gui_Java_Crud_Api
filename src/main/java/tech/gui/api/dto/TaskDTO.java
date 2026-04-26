package tech.gui.api.dto;

import jakarta.validation.constraints.NotBlank;
import tech.gui.api.entity.Task;

public class TaskDTO {
    @NotBlank(message = "Descrição é obrigatória")
    private String description;

    public TaskDTO() {
    }

    public TaskDTO(Task task) {
        this.description = task.getDescription();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


