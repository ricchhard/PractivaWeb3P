package com.example.usuario.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserApplication> getUsers() {
        return this.userRepository.findAll();
    }

    public ResponseEntity<Object> createUser(UserApplication user) {
        Optional<UserApplication> existingUser = userRepository.findUserAplicationByName(user.getName());
        HashMap<String, Object> datos = new HashMap<>();

        if (existingUser.isPresent()) {
            datos.put("data", false);
            datos.put("message", "El usuario ya existe");
            return new ResponseEntity<>(datos, HttpStatus.CONFLICT);
        } else {
            UserApplication savedUser = userRepository.save(user);
            datos.put("data", savedUser);
            datos.put("message", "Usuario creado");

            return new ResponseEntity<>(datos, HttpStatus.CREATED);
        }
    }

    public ResponseEntity<Object> updateUser(UserApplication user) {
        Optional<UserApplication> existingUser = userRepository.findById(user.getId());
        HashMap<String, Object> datos = new HashMap<>();

        if (existingUser.isPresent()) {
            UserApplication savedUser = userRepository.save(user);
            datos.put("data", savedUser);
            datos.put("message", "Usuario actualizado");

            return new ResponseEntity<>(datos, HttpStatus.OK);
        } else {
            datos.put("data", false);
            datos.put("message", "El usuario con ID " + user.getId() + " no existe");
            return new ResponseEntity<>(datos, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> deleteUser(Long userId) {
        boolean exists = userRepository.existsById(userId);
        HashMap<String, Object> datos = new HashMap<>();

        if (!exists) {
            datos.put("data", false);
            datos.put("message", "El usuario con id " + userId + " no existe");
            return new ResponseEntity<>(datos, HttpStatus.NOT_FOUND);
        }

        userRepository.deleteById(userId);
        datos.put("data", true);
        datos.put("message", "El usuario con id " + userId + " ha sido eliminado");
        return new ResponseEntity<>(datos, HttpStatus.OK);
    }
}
