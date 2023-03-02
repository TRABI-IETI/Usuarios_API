package com.edu.eci.proyectoYeti.proyectoYeti.services;

import com.edu.eci.proyectoYeti.proyectoYeti.model.User;
import com.edu.eci.proyectoYeti.proyectoYeti.model.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserServices {
    List<User> getAllUsers();

    Optional<User> getById(String id);

    User saveUser(UserDto user);

    Optional<User> updateUser(UserDto user, String id);

    void deleteUser(String id);
}
