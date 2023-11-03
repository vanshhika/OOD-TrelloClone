package com.OOD.TrelloClone.controller;

import com.OOD.TrelloClone.model.UserEntity;
import com.OOD.TrelloClone.repository.TaskEntityRepository;
import com.OOD.TrelloClone.repository.UserEntityRepository;
import com.OOD.TrelloClone.service.TaskServices;
import com.OOD.TrelloClone.service.UserServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserEntityController {
    private final UserServices userServices;

    public UserEntityController(
            UserServices userServices
    ) {
        this.userServices = userServices;
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        return ResponseEntity.ok(userServices.getallUsers());
    }
    @PostMapping("/createUser")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity userEntity) {
        return ResponseEntity.ok().body(userServices.addUser(userEntity));
    }
    @GetMapping("/getUserById/{userId}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long userId){
        return ResponseEntity.ok().body(userServices.getUser(userId));
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestParam long userID) {
        return ResponseEntity.ok().body(userServices.deleteUser(userID));
    }
    @GetMapping("/enoughUsers")
    public ResponseEntity<String> enoughUser(){
        return ResponseEntity.ok().body(userServices.enoughUsers());
    }
}
