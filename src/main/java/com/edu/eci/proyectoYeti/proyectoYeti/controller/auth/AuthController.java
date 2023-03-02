package com.edu.eci.proyectoYeti.proyectoYeti.controller.auth;

import com.edu.eci.proyectoYeti.proyectoYeti.exceptions.InvalidCredentialsException;
import com.edu.eci.proyectoYeti.proyectoYeti.model.User;
import com.edu.eci.proyectoYeti.proyectoYeti.services.UserServices;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static com.edu.eci.proyectoYeti.proyectoYeti.utils.Constants.CLAIMS_ROLES_KEY;
import static com.edu.eci.proyectoYeti.proyectoYeti.utils.Constants.TOKEN_DURATION_MINUTES;


@RestController
@RequestMapping( "v1/auth" )
public class AuthController
{

    @Value( "${app.secret}" )
    String secret;

    private final UserServices userService;

    public AuthController( @Autowired UserServices userService )
    {
        this.userService = userService;
    }

    @PostMapping
    public TokenDto login( @RequestBody LoginDto loginDto )
    {
        List<User> users = userService.getAllUsers();
//        System.out.println(users.get(0).getName());
        User newUser = new User();
        for(User user: users){
            if(Objects.equals(user.getMail(), loginDto.getEmail())){
                newUser = user;
            }
        }
        Optional<User> user = userService.getById(newUser.getId());
        if ( BCrypt.checkpw( loginDto.password, user.get().getPasswordHash() ) )
        {
            return generateTokenDto( user.get() );
        }
        else
        {
            throw new InvalidCredentialsException();
        }

    }

    private String generateToken( User user, Date expirationDate )
    {
        return Jwts.builder()
                .setSubject( user.getId() )
                .claim( CLAIMS_ROLES_KEY, "USER" )
                .setIssuedAt(new Date() )
                .setExpiration( expirationDate )
                .signWith( SignatureAlgorithm.HS256, secret )
                .compact();
    }

    private TokenDto generateTokenDto( User user )
    {
        Calendar expirationDate = Calendar.getInstance();
        expirationDate.add( Calendar.MINUTE, TOKEN_DURATION_MINUTES );
        String token = generateToken( user, expirationDate.getTime() );
        return new TokenDto( token, expirationDate.getTime() );
    }
}
