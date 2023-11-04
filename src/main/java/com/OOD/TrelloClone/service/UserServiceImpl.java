package com.OOD.TrelloClone.service;

import com.OOD.TrelloClone.model.TaskEntity;
import com.OOD.TrelloClone.model.TaskUsersEntity;
import com.OOD.TrelloClone.model.UserEntity;
import com.OOD.TrelloClone.repository.TaskEntityRepository;
import com.OOD.TrelloClone.repository.TaskUserEntityRepository;
import com.OOD.TrelloClone.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServices {
    //Singleton pattern
    private static UserServiceImpl instance;

    private final UserEntityRepository userEntityRepository;

    private final TaskEntityRepository taskEntityRepository;

    private  final  TaskUserEntityRepository taskUserEntityRepository;


    public static UserServiceImpl getInstance(UserEntityRepository userEntityRepository,TaskEntityRepository taskEntityRepository,  TaskUserEntityRepository taskUserEntityRepository) {
        if (instance == null) {
            instance = new UserServiceImpl(userEntityRepository ,taskEntityRepository, taskUserEntityRepository);
        }
        return instance;
    }

    @Override
    public UserEntity addUser(UserEntity userDetails) {
        return userEntityRepository.save(userDetails);
    }
    @Override
    public UserEntity getUser(Long userID) {
        return userEntityRepository.findUserEntityByUserID(userID);
    }
    @Override
    public List<UserEntity> getallUsers(){
        List<UserEntity> allUsers = new ArrayList<>();
        allUsers = userEntityRepository.findAll();
        return allUsers;
    }
    @Override
    public String enoughUsers(){
        int numberOfUsers= getallUsers().size();
        if(numberOfUsers>=4){
            return "There are enough Users";
        }
        return "There are not enough Users";
    }
    @Override
    public String deleteUser(Long userID){
        UserEntity user = userEntityRepository.findUserEntityByUserID(userID);
        if(user==null)
            return "NO such user exists";
        else {
            List<TaskUsersEntity> task_user = taskUserEntityRepository.findByUserDetails(user);
            List<TaskEntity> task = taskEntityRepository.findAll();
            for(TaskEntity t :task){
                List<UserEntity> getAssigned = t.getAssignedTo();
                List<UserEntity> assigned = new ArrayList<>();
                for(UserEntity assignedUser: getAssigned){
                    if(assignedUser.getUserID() != userID){
                        assigned.add(assignedUser);
                    }
                }
                t.setAssignedTo(assigned);
            }
            for(TaskUsersEntity taskUser: task_user){
                taskUserEntityRepository.delete(taskUser);
            }
            userEntityRepository.deleteById(userID);
        }
        return "User Deleted Successfully";
    }
}
