package com.OOD.TrelloClone.repository;

import com.OOD.TrelloClone.model.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskEntityRepository extends JpaRepository<TaskEntity, Long> {
    TaskEntity findTaskEntityByTaskID(long TaskID);
}
