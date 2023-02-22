package com.edu.eci.proyectoYeti.proyectoYeti.services.Impl;

import com.edu.eci.proyectoYeti.proyectoYeti.model.User;
import com.edu.eci.proyectoYeti.proyectoYeti.services.UserServices;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServicesImpl implements UserServices {

    Map<String, User> users = new HashMap<>();

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    @Override
    public Optional<User> getById(String id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public User saveUser(User user) {
        if(getById(user.getId()).isEmpty()){
            users.put(user.getId(),user);
        }
        return users.get(user.getId());
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
