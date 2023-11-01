package com.OOD.TrelloClone.service;

import com.OOD.TrelloClone.model.RequestArgs.CreateTask;
import com.OOD.TrelloClone.model.RequestArgs.ModifyTask;
import org.springframework.http.ResponseEntity;

public interface TaskServices {
    String addTask(CreateTask task);
    String updateTask(ModifyTask modifyTask);

    String TimeToDoing(long TaskId);
    String TimeToDone(long TaskId);

}
