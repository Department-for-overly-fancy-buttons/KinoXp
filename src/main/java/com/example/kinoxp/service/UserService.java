package com.example.kinoxp.service;

import com.example.kinoxp.exceptions.NotFoundException;
import com.example.kinoxp.model.User;
import com.example.kinoxp.model.Role;
import com.example.kinoxp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getAllUsersByRole(Role role) {
          return userRepository.findByRole(role.toString());
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found with id: " + id));
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public User updateUserRole(Long id, User user) {
        User newUser = getUserById(id);
        newUser.setRole(user.getRole());
        return userRepository.save(newUser);
    }

    public User updateUserLogin(Long id, User user) {
        User newUser = getUserById(id);
        newUser.setUsername(user.getUsername());
        newUser.setPasswords(user.getPasswords());
        return userRepository.save(newUser);
    }


}
