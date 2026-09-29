package com.example.recipebox.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.recipebox.entity.User;
import com.example.recipebox.exception.ResourceNotFoundException;
import com.example.recipebox.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "User not found with id: " + id
                    )
                );
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User user) {

        User existing = getUserById(id);

        existing.setName(user.getName());
        existing.setEmail(user.getEmail());
        existing.setPassword(user.getPassword());

        return userRepository.save(existing);
    }

    public void deleteUser(Long id) {

        User user = getUserById(id);

        userRepository.delete(user);
    }
}