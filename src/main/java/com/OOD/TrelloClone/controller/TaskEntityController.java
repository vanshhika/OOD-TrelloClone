package com.OOD.TrelloClone.controller;

import com.OOD.TrelloClone.model.TaskEntity;
import com.OOD.TrelloClone.model.TaskState;
import com.OOD.TrelloClone.model.UserEntity;
import com.OOD.TrelloClone.repository.TaskEntityRepository;
import com.OOD.TrelloClone.repository.UserEntityRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskEntityController {
    private final TaskEntityRepository taskEntityRepository;
    private final UserEntityRepository userEntityRepository;

    public TaskEntityController(TaskEntityRepository taskEntityRepository, UserEntityRepository userEntityRepository) {
        this.taskEntityRepository = taskEntityRepository;
        this.userEntityRepository = userEntityRepository;
    }

    private String currentTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // Define your desired date-time format
        return currentDateTime.format(formatter);
    }

    @PostMapping("/createTask")
    public ResponseEntity<Long> createTask(
            @RequestParam String description,
            @RequestParam String taskName,
            @RequestParam List<String> comments,
            @RequestParam Long userId) {

        TaskEntity task = new TaskEntity();
        task.setDescription(description);
        task.setState(TaskState.TODO);
        task.setTodoTime(currentTime()); // You can implement this method
        task.setTaskName(taskName);
        task.setComments(comments);
        UserEntity user = userEntityRepository.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        task.setAssignedTo(user);
        taskEntityRepository.save(task);
        return ResponseEntity.ok(task.getTaskID());
    }

    @PutMapping("/modifyTask/{taskId}")
    public ResponseEntity<String> modifyTask(
            @PathVariable Long taskId,
            @RequestParam Long assignedTo,
            @RequestParam String description,
            @RequestParam List<String> comments) {

        TaskEntity task = taskEntityRepository.findById(taskId).orElse(null);
        if (task == null) {
            return ResponseEntity.notFound().build();
        }

        if (assignedTo != null) {
            UserEntity user = userEntityRepository.findById(assignedTo).orElse(null);
            if (user == null) {
                return ResponseEntity.badRequest().body("User not found");
            }
            task.setAssignedTo(user);
        }

        if (description != null) {
            task.setDescription(description);
        }

        if (comments != null) {
            task.getComments().addAll(comments);
        }

        taskEntityRepository.save(task);
        return ResponseEntity.ok("Task modified successfully");
    }
    @DeleteMapping("/deleteTask/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Long taskId) {
        taskEntityRepository.deleteById(taskId);
        return ResponseEntity.ok("Task deleted successfully");
    }

    @GetMapping("/getAllTasks")
    public ResponseEntity<List<TaskEntity>> getAllTasks() {
        List<TaskEntity> tasks = taskEntityRepository.findAll();
        return ResponseEntity.ok(tasks);
    }
}
