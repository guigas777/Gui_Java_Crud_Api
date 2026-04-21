package tech.gui.api.dto;

import jakarta.validation.constraints.NotBlank;

public class TaskDTO {
    @NotBlank(message = "Descrição é obrigatória")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


