package com.edu.eci.proyectoYeti.proyectoYeti.services.Impl;

import com.edu.eci.proyectoYeti.proyectoYeti.model.User;
import com.edu.eci.proyectoYeti.proyectoYeti.persistence.UserRepository;
import com.edu.eci.proyectoYeti.proyectoYeti.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    UserRepository userRepository;

    Map<String, User> users = new HashMap<>();

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getById(String id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public Optional<User> saveUser(User user) {
        userRepository.save(user);
        return userRepository.findById(user.getId());
    }

    @Override
    public User updateUser(User newUser, String id) {
        User oldUser = users.get(id);
        oldUser.update(newUser);
        return users.get(id);
    }

    @Override
    public void deleteUser(String id) {
        users.remove(id);
    }

}
