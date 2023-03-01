package com.edu.eci.proyectoYeti.proyectoYeti.services;

import com.edu.eci.proyectoYeti.proyectoYeti.model.User;

import java.util.List;
import java.util.Optional;

public interface UserServices {
    List<User> getAllUsers();

    Optional<User> getById(String id);

    Optional<User> saveUser(User user);

    User updateUser(User user, String id);

    void deleteUser(String id);
}
