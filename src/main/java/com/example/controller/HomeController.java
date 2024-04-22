package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @GetMapping("/admin")
    public String index() {return "admin/index";}

    @GetMapping("/home")
    public String home(Model model) {

        return "home/index";
    }
}
