package com.OOD.TrelloClone.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity {
    public long getTaskID() {
        return taskID;
    }

    public void setTaskID(long taskID) {
        this.taskID = taskID;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTodoTime() {
        return todoTime;
    }

    public void setTodoTime(String todoTime) {
        this.todoTime = todoTime;
    }

    public String getDoneTime() {
        return doneTime;
    }

    public void setDoneTime(String doneTime) {
        this.doneTime = doneTime;
    }

    public String getDoingTime() {
        return doingTime;
    }

    public void setDoingTime(String doingTime) {
        this.doingTime = doingTime;
    }

    public TaskState getState() {
        return state;
    }

    public void setState(TaskState state) {
        this.state = state;
    }

    public List<UserEntity> getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(List<UserEntity> assignedTo) {
        this.assignedTo = assignedTo;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

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
    //@ManyToOne
    //private UserEntity assignedTo;
    @ManyToMany
    @ElementCollection
    private List<UserEntity> assignedTo;
    @ElementCollection
    private List<String> comments;

}
