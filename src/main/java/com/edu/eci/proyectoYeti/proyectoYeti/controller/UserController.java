package com.edu.eci.proyectoYeti.proyectoYeti.controller;

import com.edu.eci.proyectoYeti.proyectoYeti.exceptions.UserException;
import com.edu.eci.proyectoYeti.proyectoYeti.model.User;
import com.edu.eci.proyectoYeti.proyectoYeti.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserServices userServices;

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(userServices.getAllUsers());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") String id) {
        User user = userServices.getById(id).orElseThrow(()->new UserException(id));
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user){
        Optional<User> newUser = userServices.saveUser(user);
        URI createUserURI = URI.create("");
        return ResponseEntity.created(createUserURI).body(newUser);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable("id") String id){
        User updateUser = userServices.updateUser(user,id).orElseThrow(() -> new UserException(id));
        return ResponseEntity.ok(updateUser);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") String id){
        User user = userServices.getById(id).orElseThrow(()->new UserException(id));
        userServices.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
