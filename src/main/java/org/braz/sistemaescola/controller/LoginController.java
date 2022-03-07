package org.braz.sistemaescola.controller;

import org.braz.sistemaescola.entities.Usuario;
import org.braz.sistemaescola.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public LoginController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/login")
    public String getLogin(Model model){
        model.addAttribute("usuario", new Usuario());
        return "pages/login";
    }

//    @PostMapping("/login")
//    public String loginSubmit(@ModelAttribute Usuario usuario, Model model){
//        return "pages/user";
//    }
}
