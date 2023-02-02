package com.edu.eci.proyectoYeti.proyectoYeti.controller.health;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/health")
public class HealthController {

    @RequestMapping(method = RequestMethod.GET)
    public String checkAPI(){
        return "<h1>The API is working ok!</h1>";
    }
}
