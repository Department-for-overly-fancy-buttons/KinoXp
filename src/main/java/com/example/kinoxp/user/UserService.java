package com.example.kinoxp.user;

import com.example.kinoxp.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private Role stringToRole(String roleText){
        switch (roleText){
            case "CUSTOMER":
                return Role.CUSTOMER;
            case "EMPLOYEE":
                return Role.EMPLOYEE;
            case "ADMIN":
                return Role.ADMIN;
            default:
                return null;
        }
    }

    public User createUser(CreateUserRequest userRequest) {
        Role requestedRole = stringToRole(userRequest.role());
        if(requestedRole == null){
            return null;
        }
        return userRepository.save(new User(userRequest.username(), userRequest.password(), requestedRole));
    }

    List<User> getAllUsers() {
        return userRepository.findAll();
    }

    List<User> getAllUsersByRole(Role role) {
          return userRepository.findByRole(role.toString());
    }

    User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found with id: " + id));
    }

    void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    User updateUserLogin(Long id, CreateUserRequest userRequest) {
        User newUser = getUserById(id);
        newUser.setUsername(userRequest.username());
        newUser.setPassword(userRequest.password());
        newUser.setRole(stringToRole(userRequest.role()));

        if(newUser.getRole() == null){
            return null;
        }

        return userRepository.save(newUser);
    }

    User logIn(String username,String password){
        return userRepository.findByUsernameAndPassword(username, password);
    }
}
