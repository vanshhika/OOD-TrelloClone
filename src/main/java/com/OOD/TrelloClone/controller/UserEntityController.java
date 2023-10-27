package com.OOD.TrelloClone.controller;

import com.OOD.TrelloClone.repository.UserEntityRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/getAllUsers")
public class UserEntityController {
    private final UserEntityRepository userEntityRepository;

    public UserEntityController(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @GetMapping
    public ResponseEntity getAllUsers() {
        return ResponseEntity.ok(this.userEntityRepository.findAll());
    }
}
