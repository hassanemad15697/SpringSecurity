package com.spring.controller;


import com.spring.security.ResponseLogin;
import com.spring.security.jwt.JWT_Login;
import com.spring.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.io.IOException;

@RestController
public class API_System {


    private  TokenService tokenService;
    @Autowired
    public API_System(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/logintest")
    public ResponseLogin logintest(@RequestBody JWT_Login jwt_login) throws Exception {
        System.out.println("from logintest");
        return tokenService.successfulAuthentication(jwt_login);
    }
}
