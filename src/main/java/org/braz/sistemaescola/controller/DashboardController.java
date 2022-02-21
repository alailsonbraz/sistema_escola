package org.braz.sistemaescola.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard.html")
    public String home(){
        return "dashboard";
    }

    @GetMapping("/user.html")
    public String usuario(){
        return "user";
    }

    @GetMapping("/cursos.html")
    public String cursos(){
        return "cursos";
    }

    @GetMapping("/register.html")
    public String register(){
        return "register";
    }
}
