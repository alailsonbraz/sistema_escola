package org.braz.sistemaescola.controller;

import org.braz.sistemaescola.entities.Aluno;
import org.braz.sistemaescola.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/registo/aluno")
    public String showRegisterAlunoForm(Model model){
        model.addAttribute("usuario", new Aluno());
        return "pages/registeraluno";
    }

    @PostMapping("/registo/aluno")
    public String addUser(@Validated Aluno usuario, BindingResult result, Model model){
        if(result.hasErrors()){
            return "registeraluno";
        }

        usuarioRepository.save(usuario);
        return "redirect:/registo";
    }

    @GetMapping("/registerProfessor.html")
    public String registoProfessor(){
        return "registerProfessor";
    }



}
