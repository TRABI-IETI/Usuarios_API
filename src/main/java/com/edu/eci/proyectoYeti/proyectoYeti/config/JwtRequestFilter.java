package com.edu.eci.proyectoYeti.proyectoYeti.config;

import com.edu.eci.proyectoYeti.proyectoYeti.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private static final Object COOKIE_NAME = "Auth";
    @Value( "${app.secret}" )
    String secret;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader( HttpHeaders.AUTHORIZATION );
        try{
            Optional<Cookie> optionalCookie =
                    request.getCookies() != null ? Arrays.stream( request.getCookies() ).filter(
                            cookie -> Objects.equals( cookie.getName(), COOKIE_NAME ) ).findFirst() : Optional.empty();
            String header = null;
            if(authHeader != null && authHeader.startsWith( "Bearer" )){
                header = authHeader.substring( 7 );
            }
            String token = optionalCookie.isPresent() ? optionalCookie.get().getValue() : header;
            if(token != null){
                Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws( token );
                Claims claimsBody = claims.getBody();
                String subject = claimsBody.getSubject();
                List<String> roles  = new ArrayList<>();
                roles.add("ADMIN");roles.add("USER");
//                SecurityContextHolder.getContext().setAuthentication(new TestingAuthenticationToken(subject, token, "ADMIN"));
                if (roles == null) {
                    response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid token roles");
                } else {
                    SecurityContextHolder.getContext().setAuthentication( new TokenAuthentication( token, subject, roles));

                }
                System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
                request.setAttribute( "claims", claimsBody);
                request.setAttribute( "jwtUserId", subject);
                request.setAttribute("jwtUserRoles", roles);
            }
            filterChain.doFilter( request, response );
        }catch (Exception e){
            e.printStackTrace();
//            System.out.println();
        }
    }
}
