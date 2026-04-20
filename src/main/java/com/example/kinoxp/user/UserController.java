package com.example.kinoxp.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8090")
@RequestMapping("/api/users")
@RestController
class UserController {

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    ResponseEntity<User> addUser(@RequestBody CreateUserRequest userRequest) {
        User addedUser = userService.createUser(userRequest);
        if (addedUser == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(addedUser);
    }

    @PutMapping({"update/{id}"})
    ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody CreateUserRequest userRequest) {
        User updateUser = userService.updateUserLogin(id, userRequest);
        if (updateUser == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(updateUser);
    }


    @GetMapping
    ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }


    @GetMapping("/role/{role}")
    ResponseEntity<List<User>> getAllUsersByRole(@PathVariable Role role) {
        return ResponseEntity.ok(userService.getAllUsersByRole(role));
    }


    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/log_in")
    ResponseEntity<User> logIn(@RequestBody User user) {
        User loggedInUser = userService.logIn(user.getUsername(), user.getPassword());
        if (loggedInUser != null) {
            return ResponseEntity.ok(loggedInUser);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/roles")
    ResponseEntity<Role[]> getRoles() {
        return ResponseEntity.ok(Role.values());
    }

}
