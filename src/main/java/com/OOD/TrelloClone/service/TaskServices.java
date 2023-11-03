package com.OOD.TrelloClone.service;

import com.OOD.TrelloClone.model.RequestArgs.CreateTask;
import com.OOD.TrelloClone.model.RequestArgs.ModifyTask;
import com.OOD.TrelloClone.model.TaskEntity;
import com.OOD.TrelloClone.model.UserEntity;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public interface TaskServices {
    String addTask(CreateTask task);
    String updateTask(ModifyTask modifyTask);

    String TimeToDoing(long TaskId);
    String TimeToDone(long TaskId);
    List<TaskEntity> getallTask();
    String deleteTask(Long TaskID);

}
