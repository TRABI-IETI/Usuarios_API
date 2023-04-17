package com.edu.eci.proyectoYeti.proyectoYeti.controller;

import com.edu.eci.proyectoYeti.proyectoYeti.exceptions.UserException;
import com.edu.eci.proyectoYeti.proyectoYeti.model.User;
import com.edu.eci.proyectoYeti.proyectoYeti.model.UserDto;
import com.edu.eci.proyectoYeti.proyectoYeti.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("v1/users")
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
    public ResponseEntity<?> createUser(@RequestBody UserDto user){
        User newUser = userServices.saveUser(user);
        URI createUserURI = URI.create("");
        return ResponseEntity.created(createUserURI).body(newUser);
    }

    @PostMapping("{id}/{package}")
    public ResponseEntity<?> addPackage(@PathVariable("id") String id,  @PathVariable("package") String paquete ){
        return ResponseEntity.ok(userServices.addPackage(id, paquete));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateUser(@RequestBody UserDto user, @PathVariable("id") String id){
        User updateUser = userServices.updateUser(user,id).orElseThrow(() -> new UserException(id));
        return ResponseEntity.ok(updateUser);
    }

    @DeleteMapping("{id}/{package}")
    public ResponseEntity<?> deletePackage(@PathVariable("id") String id,  @PathVariable("package") String paquete ){
        return ResponseEntity.ok(userServices.DeletePackage(id, paquete));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") String id){
        userServices.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{username}/{password}")
    public ResponseEntity<?> login(@PathVariable("username") String user, @PathVariable("password") String password){
        return ResponseEntity.ok(userServices.login(user, password));
    }
}
