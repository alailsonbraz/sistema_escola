package org.braz.sistemaescola.controller;

import org.braz.sistemaescola.entities.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/")
    public String getLogin(Model model){
        model.addAttribute("usuario", new Usuario());
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute Usuario usuario, Model model){
        System.out.println(usuario.getEmail());
        System.out.println(usuario.getPassword());
        return "home";
    }
}
