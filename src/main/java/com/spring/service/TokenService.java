package com.spring.service;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.security.ResponseLogin;
import com.spring.security.UserPrincipal;
import com.spring.security.jwt.JWT_Login;
import com.spring.security.jwt.JWT_Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

@Service
public class TokenService {

    private AuthenticationManager authenticationManager;

    @Autowired
    public TokenService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public Authentication attemptAuthentication(JWT_Login jwtLogin) throws AuthenticationException {

        // Create login token
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                jwtLogin.getUsername(),
                jwtLogin.getPassword(),
                new ArrayList<>());

        // Authenticate user
        Authentication auth = authenticationManager.authenticate(authenticationToken);

        return auth;
    }

    public ResponseLogin successfulAuthentication(JWT_Login jwtLogin) throws Exception {
        ResponseLogin responseLogin  = new ResponseLogin();
        Authentication authResult = attemptAuthentication(jwtLogin);

        // Grab principal
        UserPrincipal principal = (UserPrincipal) authResult.getPrincipal();

        responseLogin.setUsername(jwtLogin.getUsername());

        // Create JWT Token
        String token = JWT.create()
                .withSubject(principal.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JWT_Properties.EXPIRATION_TIME))
                .sign(HMAC512(JWT_Properties.SECRET.getBytes()));

        responseLogin.setToken(token);
        return responseLogin;
    }
}
