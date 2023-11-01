package com.OOD.TrelloClone.controller;

import com.OOD.TrelloClone.model.UserEntity;
import com.OOD.TrelloClone.repository.UserEntityRepository;
import com.OOD.TrelloClone.service.UserServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserEntityController {
    private final UserEntityRepository userEntityRepository;
    private final UserServices userServices;

    public UserEntityController(UserEntityRepository userEntityRepository, UserServices userServices) {
        this.userEntityRepository = userEntityRepository;
        this.userServices = userServices;
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.ok(this.userEntityRepository.findAll());
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
        userEntityRepository.deleteById(userID);
        return ResponseEntity.ok("User deleted successfully");
    }

}
