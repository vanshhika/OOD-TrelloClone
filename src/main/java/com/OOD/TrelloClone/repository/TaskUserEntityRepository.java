package com.OOD.TrelloClone.repository;

import com.OOD.TrelloClone.model.TaskEntity;
import com.OOD.TrelloClone.model.TaskUsersEntity;
import com.OOD.TrelloClone.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskUserEntityRepository extends JpaRepository<TaskUsersEntity, Long> {
    List<TaskUsersEntity> findByTask(TaskEntity task);
    List<TaskUsersEntity> findByUserDetails(UserEntity userDetails);
}
