package com.OOD.TrelloClone.service;

import com.OOD.TrelloClone.model.UserEntity;

import java.util.List;

public interface UserServices {
    UserEntity addUser(UserEntity userEntity);

    UserEntity getUser(Long userID);
    List<UserEntity> getallUsers();

    String enoughUsers();
    String deleteUser(Long userID);
}
