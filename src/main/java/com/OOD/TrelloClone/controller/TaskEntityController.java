package com.OOD.TrelloClone.controller;

import com.OOD.TrelloClone.model.RequestArgs.CreateTask;
import com.OOD.TrelloClone.model.RequestArgs.ModifyTask;
import com.OOD.TrelloClone.model.TaskEntity;
import com.OOD.TrelloClone.repository.TaskEntityRepository;
import com.OOD.TrelloClone.service.TaskServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskEntityController {
    private final TaskEntityRepository taskEntityRepository;
    private final TaskServices taskServices;

    public TaskEntityController(
            TaskEntityRepository taskEntityRepository,
            TaskServices taskServices
    ) {
        this.taskEntityRepository = taskEntityRepository;
        this.taskServices = taskServices;
    }

    @PostMapping("/createTask")
    public ResponseEntity<String> createTask(@RequestBody CreateTask task) {
        return ResponseEntity.ok().body(taskServices.addTask(task));
    }
    @GetMapping("/getTask/{taskId}")
    public ResponseEntity<TaskEntity> getTask(@PathVariable Long taskId){
        return ResponseEntity.ok().body((taskEntityRepository.findTaskEntityByTaskID(taskId)));
    }
    @PutMapping("/modifyTask")
    public ResponseEntity<String> modifyTask(@RequestBody ModifyTask modifyTask) {
        return ResponseEntity.ok().body(taskServices.updateTask(modifyTask));
    }
    @DeleteMapping("/deleteTask/{taskId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long taskID) {
        return ResponseEntity.ok().body(taskServices.deleteTask(taskID));
    }

    @GetMapping("/getAllTasks")
    public ResponseEntity<List<TaskEntity>> getAllTasks() {
        return ResponseEntity.ok(taskServices.getallTask());
    }
    @GetMapping("/timeToDoing/{taskId}")
    public ResponseEntity<String> timeToDoing(@PathVariable long taskId){
        return ResponseEntity.ok().body(taskServices.TimeToDoing(taskId));
    }
    @GetMapping("/timeToDone/{taskId}")
    public ResponseEntity<String> timeToDone(@PathVariable long taskId){
        return ResponseEntity.ok().body(taskServices.TimeToDone(taskId));
    }
}
