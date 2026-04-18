package tech.gui.api.service;

import org.springframework.stereotype.Service;
import tech.gui.api.entity.Task;
import tech.gui.api.repository.TaskRepository;

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
}
