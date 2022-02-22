package org.braz.sistemaescola.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/home")
    public String home(){
        return "pages/index";
    }

    @GetMapping("/user.html")
    public String usuario(){
        return "user";
    }

    @GetMapping("/cursos.html")
    public String cursos(){
        return "cursos";
    }

    @GetMapping("/registo")
    public String register(){
        return "pages/register";
    }
}
