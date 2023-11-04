package com.OOD.TrelloClone.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private long taskID;
    @NonNull
    private String taskName;
    private String description;
    private String todoTime;
    private String doneTime;
    private String doingTime;
    @Enumerated(EnumType.STRING)
    private TaskState state;
    @ManyToMany
    @JoinTable(name = "task_entity_assigned_to",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<UserEntity> assignedTo;
    @ElementCollection
    private List<String> comments;

}
