package com.example.kinoxp.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost")
@RequestMapping("/api/users")
@RestController
class UserController {

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    ResponseEntity<KinoUser> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/user")
    UserResponse getUser(Authentication authentication) {
        String username = authentication.getName();
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return new UserResponse(username, roles);
    }

    @PostMapping
    ResponseEntity<KinoUser> addUser(@RequestBody CreateUserRequest userRequest) {
        KinoUser addedUser = userService.createUser(userRequest);
        if (addedUser == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(addedUser);
    }

    @PutMapping({"update/{id}"})
    ResponseEntity<KinoUser> updateUser(@PathVariable Long id, @RequestBody CreateUserRequest userRequest) {
        KinoUser updateUser = userService.updateUserLogin(id, userRequest);
        if (updateUser == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(updateUser);
    }


    @GetMapping
    ResponseEntity<List<KinoUser>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }


    @GetMapping("/role/{role}")
    ResponseEntity<List<KinoUser>> getAllUsersByRole(@PathVariable Role role) {
        return ResponseEntity.ok(userService.getAllUsersByRole(role));
    }


    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/log_in")
    ResponseEntity<KinoUser> logIn(@RequestBody KinoUser user) {
        KinoUser loggedInUser = userService.logIn(user.getUsername(), user.getPassword());
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
