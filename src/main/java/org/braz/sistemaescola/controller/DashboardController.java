package org.braz.sistemaescola.controller;

import org.braz.sistemaescola.repository.CursoRepository;
import org.braz.sistemaescola.repository.DisciplinaRepository;
import org.braz.sistemaescola.repository.TurmaRepository;
import org.braz.sistemaescola.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    DisciplinaRepository disciplinaRepository;

    @Autowired
    CursoRepository cursoRepository;

    @Autowired
    TurmaRepository turmaRepository;

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
    public String register(Model model){
        model.addAttribute("count_students", usuarioRepository.countByAluno());
        model.addAttribute("count_professors", usuarioRepository.countByProfessor());
        model.addAttribute("count_subjects", disciplinaRepository.countByDisciplina());
        model.addAttribute("count_courses", cursoRepository.countByCurso());
        model.addAttribute("count_courseclass", turmaRepository.countByTurma());
        return "pages/registo";
    }

}


