package org.braz.sistemaescola.controller;

import org.braz.sistemaescola.entities.*;
import org.braz.sistemaescola.repository.CursoRepository;
import org.braz.sistemaescola.repository.DisciplinaRepository;
import org.braz.sistemaescola.repository.TurmaRepository;
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

    @Autowired
    DisciplinaRepository disciplinaRepository;

    @Autowired
    CursoRepository cursoRepository;

    @Autowired
    TurmaRepository turmaRepository;

    @GetMapping("/register/student")
    public String showRegisterAlunoForm(Model model){
        model.addAttribute("usuario", new Aluno());
        model.addAttribute("cursos", cursoRepository.findAll());
        return "pages/registoaluno";
    }

    @PostMapping("/register/student")
    public String addUserAluno(@Validated Aluno usuario, BindingResult result, Model model){
        if(result.hasErrors()){
            return "pages/registoaluno";
        }

        usuario.setCurso(cursoRepository.findById(usuario.getCursoId()).get());
        usuarioRepository.save(usuario);
        return "redirect:/register";
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
        return "redirect:/register";
    }

    @GetMapping("/register/subject")
    public String showRegisterSubjectForm(Model model){
        model.addAttribute("disciplina", new Disciplina());
        return "pages/registodisciplina";
    }

    @PostMapping("/register/subject")
    public String addUSubject(@Validated Disciplina disciplina, BindingResult result, Model model){
        if(result.hasErrors()){
            return "pages/registodisciplina";
        }

        disciplinaRepository.save(disciplina);
        return "redirect:/register";
    }

    @GetMapping("/register/course")
    public String showRegisterCourseForm(Model model){
        model.addAttribute("curso", new Curso());
        return "pages/registocurso";
    }

    @PostMapping("/register/course")
    public String addUCourse(@Validated Curso curso, BindingResult result, Model model){
        if(result.hasErrors()){
            return "pages/registocurso";
        }

        cursoRepository.save(curso);
        return "redirect:/register";
    }

    @GetMapping("/register/courseclass")
    public String showRegisterCourseClassForm(Model model){
        model.addAttribute("turma", new Turma());
        return "pages/registoturma";
    }

    @PostMapping("/register/courseclass")
    public String addUCourseClass(@Validated Turma turma, BindingResult result, Model model){
        if(result.hasErrors()){
            return "pages/registoturma";
        }

        turmaRepository.save(turma);
        return "redirect:/register";
    }

}
