package com.OOD.TrelloClone.service;

import com.OOD.TrelloClone.model.RequestArgs.CreateTask;
import com.OOD.TrelloClone.model.RequestArgs.ModifyTask;
import com.OOD.TrelloClone.model.TaskEntity;
import com.OOD.TrelloClone.model.TaskState;
import com.OOD.TrelloClone.model.UserEntity;
import com.OOD.TrelloClone.repository.TaskEntityRepository;
import com.OOD.TrelloClone.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskServices{
    private final UserEntityRepository userEntityRepository;
    private final TaskEntityRepository taskEntityRepository;

    public String gettime(){
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentTime.format(formatter);
        return formattedDateTime;
    }

    @Override
    public String addTask(CreateTask createTask){
        //Get current time and date

        TaskEntity task = new TaskEntity();
        task.setDescription(createTask.getDescription());
        task.setState(TaskState.TODO);
        task.setTodoTime(gettime()); // You can implement this method
        task.setTaskName(createTask.getTaskName());
        task.setComments(createTask.getComments());
        UserEntity user = userEntityRepository.findById(createTask.getAssignedTo()).orElse(null);
        if(createTask.getAssignedTo()!=0 && user==null){
            return "Please enter a USer to do the task";
        }
        if (user == null) {
            return "The User does not exist!! Try again!!";
        }
        task.setAssignedTo(user);
        taskEntityRepository.save(task);
        return "Task Created Successfully";
    }

    @Override
    public String updateTask(ModifyTask modifytask){
        TaskEntity task = taskEntityRepository.findById(modifytask.getTaskId()).orElse(null);
        if (task == null) {
            return "Task with ID "+modifytask.getTaskId()+ " not found";
        }
        if (!modifytask.getTaskName().isEmpty()) {
            task.setTaskName(modifytask.getTaskName());
        }

        if (modifytask.getAssignedTo()!=0) {
            UserEntity user = userEntityRepository.findById(modifytask.getAssignedTo()).orElse(null);
            if (user == null) {
                return "User not found";
            }
            task.setAssignedTo(user);
        }

        if (modifytask.getDescription() != null) {
            task.setDescription(modifytask.getDescription());
        }

        if (modifytask.getComments() != null) {
            task.getComments().addAll(modifytask.getComments());
        }
        if (modifytask.getStringstate() != null && !modifytask.getStringstate().isEmpty()) {
            System.out.println(modifytask.getStringstate());
            if(modifytask.getStringstate().equalsIgnoreCase("doing")){
                task.setState(TaskState.DOING);
                task.setDoingTime(gettime());
            }
            else if(modifytask.getStringstate().equalsIgnoreCase("done")){
                task.setState(TaskState.DONE);
                task.setDoneTime(gettime());
            }
        }

        taskEntityRepository.save(task);
        return "Task modified successfully" +gettime() +"AAAAAAAaa";
    }

}