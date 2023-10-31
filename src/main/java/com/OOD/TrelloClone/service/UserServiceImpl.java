package com.OOD.TrelloClone.service;

import com.OOD.TrelloClone.model.UserEntity;
import com.OOD.TrelloClone.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServices {
    private final UserEntityRepository userEntityRepository;
    @Override
    public UserEntity addUser(UserEntity userDetails) {
        return userEntityRepository.save(userDetails);
    }
    @Override
    public UserEntity getUser(long userID) {
        return userEntityRepository.findByUserID(userID);
    }
}
