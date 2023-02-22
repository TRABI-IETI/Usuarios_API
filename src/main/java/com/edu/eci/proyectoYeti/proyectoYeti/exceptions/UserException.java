package com.edu.eci.proyectoYeti.proyectoYeti.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserException extends ResponseStatusException {

    public UserException(String id){
        super(HttpStatus.NOT_FOUND, id);
    }
}
