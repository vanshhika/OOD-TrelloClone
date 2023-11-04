package com.OOD.TrelloClone.service;

import com.OOD.TrelloClone.model.RequestArgs.CreateTask;
import com.OOD.TrelloClone.model.RequestArgs.ModifyTask;
import com.OOD.TrelloClone.model.TaskEntity;
import com.OOD.TrelloClone.model.TaskState;
import com.OOD.TrelloClone.model.TaskUsersEntity;
import com.OOD.TrelloClone.model.UserEntity;
import com.OOD.TrelloClone.repository.TaskEntityRepository;
import com.OOD.TrelloClone.repository.TaskUserEntityRepository;
import com.OOD.TrelloClone.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskServices{
    //Singleton Pattern
    private static TaskServiceImpl instance;

    private final UserEntityRepository userEntityRepository;
    private final TaskEntityRepository taskEntityRepository;
    private final TaskUserEntityRepository taskUsersEntityRepo;

    public static TaskServiceImpl getInstance(UserEntityRepository userEntityRepository,
                                              TaskEntityRepository taskEntityRepository,
                                              TaskUserEntityRepository taskUsersEntityRepo) {
        if (instance == null) {
            instance = new TaskServiceImpl(userEntityRepository, taskEntityRepository, taskUsersEntityRepo);
        }
        return instance;
    }



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
        List<UserEntity> users = new ArrayList<>();
        for(Long id : createTask.getAssignedTo()) {
            UserEntity user = userEntityRepository.findById(id).orElse(null);
            if(id!=0 && user==null){
                return "Please enter a User to do the task";
            }
            if (user == null) {
                return "The User does not exist!! Try again!!";
            }
            users.add(user);
        }
        task.setAssignedTo(users);
        taskEntityRepository.save(task);
        TaskUsersEntity taskUsersEntity = new TaskUsersEntity();
        taskUsersEntity.setTask(task);
        taskUsersEntity.setUserDetails(users);
        taskUsersEntityRepo.save(taskUsersEntity);
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

        List<UserEntity> users = new ArrayList<>();
        for(long id : modifytask.getAssignedTo()) {
            if (id != 0) {
                UserEntity user = userEntityRepository.findById(id).orElse(null);
                if (user == null) {
                    return "User not found";
                }
                users.add(user);
            }
        }
        task.setAssignedTo(users);

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
                task.setDoneTime("");
            }
            else if(modifytask.getStringstate().equalsIgnoreCase("done")){
                task.setState(TaskState.DONE);
                task.setDoneTime(gettime());
            }
            else if(modifytask.getStringstate().equalsIgnoreCase("todo")){
                task.setState(TaskState.TODO);
                task.setDoneTime("");
                task.setDoingTime("");
            }
        }

        taskEntityRepository.save(task);
        return "Task modified successfully" +gettime();
    }
    @Override
    public String TimeToDoing(long TaskId){
        TaskEntity task = taskEntityRepository.findTaskEntityByTaskID(TaskId);
        if (task == null) {
            return "Task with ID "+TaskId+ " not found";
        }
        if(task.getDoingTime()==""||task.getDoingTime()==null){
            return "The task has not moved to doing state yet.";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime todoTime = LocalDateTime.parse(task.getTodoTime(), formatter);
        LocalDateTime doingTime = LocalDateTime.parse(task.getDoingTime(), formatter);

        // Calculate the duration between the two timestamps
        Duration duration = Duration.between(todoTime, doingTime);
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();

        String formattedDuration = hours + " hours " + minutes + " minutes";
        return "Time Taken for the task to move to DOING state is "+formattedDuration;
    }
    @Override
    public String TimeToDone(long TaskId){
        TaskEntity task = taskEntityRepository.findTaskEntityByTaskID(TaskId);
        if (task == null) {
            return "Task with ID "+TaskId+ " not found";
        }
        if(task.getDoneTime()==""||task.getDoneTime()==null){
            return "The task has not moved to done state yet.";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime todoTime = LocalDateTime.parse(task.getTodoTime(), formatter);
        LocalDateTime doneTime = LocalDateTime.parse(task.getDoneTime(), formatter);

        // Calculate the duration between the two timestamps
        Duration duration = Duration.between(todoTime, doneTime);
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();

        String formattedDuration = hours + " hours " + minutes + " minutes";
        return "Time Taken for the task to move to DONE state is "+formattedDuration;
    }
    @Override
    public List<TaskEntity> getallTask(){
        List<TaskEntity> allTask = new ArrayList<>();
        allTask = taskEntityRepository.findAll();
        return allTask;
    }
    @Override
    public String deleteTask(Long taskID){
        TaskEntity task = taskEntityRepository.findTaskEntityByTaskID(taskID);
        System.out.println(task);
        if(task==null)
            return "No such task exists";
        else
            taskEntityRepository.deleteById(taskID);
        return "Task Deleted Successfully";
    }

}
