package org.braz.sistemaescola.controller;

import org.braz.sistemaescola.config.dto.TurmaAlunoDto;
import org.braz.sistemaescola.entities.Aluno;
import org.braz.sistemaescola.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

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

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    TurmaAlunoRepository turmaAlunoRepository;

    @Autowired
    TurmaDisciplinaRepository turmaDisciplinaRepository;

    @GetMapping("/home")
    public String home(){
        return "pages/index";
    }

    @GetMapping("/")
    public String userprofile(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        model.addAttribute("usuario", usuarioRepository.findByEmail(currentPrincipalName));
        return "pages/usuario";
    }

    @GetMapping("/professors")
    public String professors(Model model){
        model.addAttribute("professores", usuarioRepository.findAll());
        return "pages/professores";
    }

    @GetMapping("/subjects")
    public String subjects(Model model){
        model.addAttribute("disciplinas", disciplinaRepository.findAll());
        return "pages/disciplinas";
    }

    @GetMapping("/courses")
    public String courses(Model model){
        model.addAttribute("cursos", cursoRepository.findAll());
        return "pages/cursos";
    }

    @GetMapping("/courseclass")
    public String courseclass(Model model){
        model.addAttribute("turmas", turmaRepository.findAll());
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

    @GetMapping("/classselection")
    public String classSelection(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        model.addAttribute("turmasdisciplinas", turmaDisciplinaRepository.turmaDisciplinaListByEmail(currentPrincipalName));
        return "pages/selecaoturma";
    }

    @GetMapping("/insertgrades/{id}")
    public String inserirNotas(@PathVariable Integer id, Model model){
        TurmaAlunoDto turmasAlunosForm = new TurmaAlunoDto(turmaAlunoRepository.turmaAlunoListByTurma(id));
        model.addAttribute("form", turmasAlunosForm);
        return "pages/inserirnotas";
    }

    @PostMapping("/insertgrades")
    public String salvarNotaspublic(@Valid TurmaAlunoDto turmaAlunoDto, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            model.addAttribute("message", "Registration Failed");
            model.addAttribute("alertClass", "alert-danger");

            return "pages/inserirnotas";
        }

        turmaAlunoRepository.saveAll(turmaAlunoDto.getTurmas());
        redirectAttributes.addFlashAttribute("message", "Success");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/classselection";
    }


    @GetMapping("/consultgrades")
    public String consultarNotas( Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        model.addAttribute("turmasalunos", turmaAlunoRepository.turmaAlunoList(currentPrincipalName));
        model.addAttribute("curso", ((Aluno)usuarioRepository.findByEmail(currentPrincipalName)).getCurso().getNome());
        return "pages/consultarnotas";
    }

}


