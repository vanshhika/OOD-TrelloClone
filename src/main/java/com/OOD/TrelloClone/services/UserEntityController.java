package com.OOD.TrelloClone.services;

import com.OOD.TrelloClone.model.UserEntity;
import com.OOD.TrelloClone.repository.UserEntityRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserEntityController {
    private final UserEntityRepository userEntityRepository;

    public UserEntityController(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.ok(this.userEntityRepository.findAll());
    }


    @PostMapping("/createUser")
    public ResponseEntity<Long> createUser(@RequestParam String userName) {
        if (userName == null) {
            return ResponseEntity.notFound().build();
        }
        UserEntity user = new UserEntity();
        user.setUserName(userName);
        userEntityRepository.save(user);
        return ResponseEntity.ok(user.getUserID());
    }

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userEntityRepository.deleteById(userId);
        return ResponseEntity.ok("User deleted successfully");
    }
}
