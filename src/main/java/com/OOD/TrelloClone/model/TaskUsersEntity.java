package com.OOD.TrelloClone.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskUsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private long userTaskID;
    @ManyToOne
    @JoinColumn(name = "taskID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NonNull
    private TaskEntity task;
    @NonNull
    @ManyToMany
    @JoinColumn(name = "userID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<UserEntity> userDetails;
}

