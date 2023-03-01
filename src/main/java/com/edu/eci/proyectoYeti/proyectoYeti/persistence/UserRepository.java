package com.edu.eci.proyectoYeti.proyectoYeti.persistence;

import com.edu.eci.proyectoYeti.proyectoYeti.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
}
