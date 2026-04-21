package tech.gui.api.service;

import org.springframework.stereotype.Service;
import tech.gui.api.entity.Task;
import tech.gui.api.repository.TaskRepository;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> listAll() {
        return repository.findAll();
    }

    public Task create(Task task) {
        return repository.save(task);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public void delete(Long id) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        repository.delete(task);
    }

    public Task getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Task não encontrada"
                ));
    }

    public Task update(Long id, Task task) {
        Task existing = getById(id);

        existing.setDescription(task.getDescription());
        existing.setCompleted(task.isCompleted());

        return repository.save(existing);
    }

    public Task markAsCompleted(Long id) {
        Task task = getById(id);
        task.setCompleted(true);
        return repository.save(task);
    }

}
