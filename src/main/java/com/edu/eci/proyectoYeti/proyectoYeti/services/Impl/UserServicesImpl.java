package com.edu.eci.proyectoYeti.proyectoYeti.services.Impl;

import com.edu.eci.proyectoYeti.proyectoYeti.exceptions.UserException;
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
        return Optional.ofNullable(userRepository.findById(id).orElseThrow(()-> new UserException(id)));
    }

    @Override
    public Optional<User> saveUser(User user) {
        userRepository.save(user);
        return userRepository.findById(user.getId());
    }

    @Override
    public Optional<User> updateUser(User newUser, String id) {
        if(userRepository.existsById(id)){
            Optional<User> odlUser = userRepository.findById(id);
            odlUser.get().update(newUser);
            userRepository.save(odlUser.get());
            return userRepository.findById(id);
        }else {
            throw new UserException(id);
        }
    }

    @Override
    public void deleteUser(String id) {
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
        }else{
            throw new UserException(id);
        }
    }

}
