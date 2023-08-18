package com.example.usuario.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserApplication> getAllUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public ResponseEntity<String> registrarUser(@RequestBody UserApplication user) {
        userService.createUser(user);
        return ResponseEntity.ok("Usuario registrado");
    }
    @PutMapping
    public ResponseEntity<Object> actualizarUser(@RequestBody UserApplication user) {
        return userService.createUser(user);
    }
    @DeleteMapping(path = "{userId}")
    public ResponseEntity<Object> eliminarUser(@PathVariable("userId") Long userId) {
        return userService.deleteUser(userId);
    }
}
