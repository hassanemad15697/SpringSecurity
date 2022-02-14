package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
public class MainController {


    @GetMapping("home")
    public String index() {
        return "index";
    }

    @GetMapping("profile")
    public String profile() {
        return "profile/index";
    }

    @GetMapping("admin")
    public String admin() {
        return "admin";
    }
    @GetMapping("management")
    public String management() {
        return "manager , admin";
    }
    @GetMapping("user")
    public String user() {
        return "user , manager , admin";
    }
}
