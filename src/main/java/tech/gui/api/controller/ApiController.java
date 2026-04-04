package tech.gui.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/tasks")
public class ApiController {

    private List<String> tasks = new ArrayList<>();

    @GetMapping
    public List<String> listTasks() {
        return tasks;
    }

    @PostMapping
    public ResponseEntity<Void> createTask(@RequestBody String task){
        tasks.add(task);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> clearTasks(){
        tasks = new ArrayList<>();
        return ResponseEntity.ok().build();
    }

}
