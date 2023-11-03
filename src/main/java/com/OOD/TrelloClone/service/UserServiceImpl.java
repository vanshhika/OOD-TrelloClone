package com.OOD.TrelloClone.service;

import com.OOD.TrelloClone.model.UserEntity;
import com.OOD.TrelloClone.repository.TaskEntityRepository;
import com.OOD.TrelloClone.repository.TaskUserEntityRepository;
import com.OOD.TrelloClone.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServices {
    //Singleton pattern
    private static UserServiceImpl instance;

    private final UserEntityRepository userEntityRepository;

    public static UserServiceImpl getInstance(UserEntityRepository userEntityRepository) {
        if (instance == null) {
            instance = new UserServiceImpl(userEntityRepository);
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
        else
            userEntityRepository.deleteById(userID);
        return "User Deleted Successfully";
    }
}
