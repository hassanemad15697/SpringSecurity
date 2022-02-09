package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
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
        return "admin/index";
    }
    @GetMapping("management")
    public String management() {
        return "management/index";
    }
}
