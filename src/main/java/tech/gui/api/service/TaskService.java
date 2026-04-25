package tech.gui.api.service;

import org.springframework.stereotype.Service;
import tech.gui.api.entity.Task;
import tech.gui.api.repository.TaskRepository;
import java.util.List;
import tech.gui.api.dto.TaskDTO;
import tech.gui.api.exception.ResourceNotFoundException;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> listAll() {
        return repository.findAll();
    }

    public Task create(TaskDTO dto) {
        Task task = new Task();
        task.setDescription(dto.getDescription());
        task.setCompleted(false);

        return repository.save(task);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public void delete(Long id) {
        Task task = getById(id);
        repository.delete(task);
    }

    public Task getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task não encontrada"));
    }

    public Task update(Long id, TaskDTO dto) {
        Task existing = getById(id);

        existing.setDescription(dto.getDescription());

        return repository.save(existing);
    }

    public Task markAsCompleted(Long id) {
        Task task = getById(id);
        task.setCompleted(true);
        return repository.save(task);
    }

}
