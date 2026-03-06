package com.example.kinoxp.service;

import com.example.kinoxp.model.Role;
import com.example.kinoxp.model.User;
import com.example.kinoxp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getAllUsersByRole(Role role) {
        return null;
    }

}
