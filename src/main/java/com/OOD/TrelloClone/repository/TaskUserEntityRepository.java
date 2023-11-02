package com.OOD.TrelloClone.repository;

import com.OOD.TrelloClone.model.TaskEntity;
import com.OOD.TrelloClone.model.TaskUsersEntity;
import com.OOD.TrelloClone.model.UserEntity;

import java.util.List;

public interface TaskUserEntityRepository {
    List<TaskUsersEntity> findByTask(TaskEntity task);
    List<TaskUsersEntity> findByUserDetails(UserEntity userDetails);
    TaskUsersEntity save(TaskUsersEntity taskUsers);
}
