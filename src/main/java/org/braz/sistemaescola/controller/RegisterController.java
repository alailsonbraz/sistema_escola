package org.braz.sistemaescola.controller;

import org.braz.sistemaescola.entities.Aluno;
import org.braz.sistemaescola.entities.Professor;
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

    @GetMapping("/register/student")
    public String showRegisterAlunoForm(Model model){
        model.addAttribute("usuario", new Aluno());
        return "pages/registoaluno";
    }

    @PostMapping("/register/student")
    public String addUserAluno(@Validated Aluno usuario, BindingResult result, Model model){
        if(result.hasErrors()){
            return "pages/registoaluno";
        }

        usuarioRepository.save(usuario);
        return "redirect:/registo";
    }

    @GetMapping("/register/professor")
    public String showRegisterProfessorForm(Model model){
        model.addAttribute("usuario", new Professor());
        return "pages/registoprofessor";
        }

    @PostMapping("/register/professor")
    public String addUserProfessor(@Validated Professor usuario, BindingResult result, Model model){
        if(result.hasErrors()){
            return "pages/registoprofessor";
        }

        usuarioRepository.save(usuario);
        return "redirect:/registo";
    }


}
