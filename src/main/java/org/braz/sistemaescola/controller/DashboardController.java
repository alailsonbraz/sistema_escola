package org.braz.sistemaescola.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/home")
    public String home(){
        return "pages/index";
    }

    @GetMapping("/userprofile")
    public String userprofile(){
        return "pages/usuario";
    }

    @GetMapping("/professors")
    public String professors(){
        return "pages/professores";
    }

    @GetMapping("/subjects")
    public String subjects(){
        return "pages/disciplinas";
    }

    @GetMapping("/courses")
    public String courses(){
        return "pages/cursos";
    }

    @GetMapping("/courseclass")
    public String courseclass(){
        return "pages/turmas";
    }

    @GetMapping("/register")
    public String register(){
        return "pages/registo";
    }

}


