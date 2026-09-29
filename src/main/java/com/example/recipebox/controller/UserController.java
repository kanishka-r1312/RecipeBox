package com.example.recipebox.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.recipebox.entity.User;
import com.example.recipebox.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = {
        "http://localhost:5500",
        "http://127.0.0.1:5500"
})
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(
            @RequestBody User user) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.createUser(user));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {

        return ResponseEntity.ok(
                userService.getAllUsers()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                userService.getUserById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable Long id,
            @RequestBody User user) {

        return ResponseEntity.ok(
                userService.updateUser(id, user)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(
            @PathVariable Long id) {

        userService.deleteUser(id);

        return ResponseEntity.ok(
                Map.of("message", "User deleted successfully")
        );
    }
}