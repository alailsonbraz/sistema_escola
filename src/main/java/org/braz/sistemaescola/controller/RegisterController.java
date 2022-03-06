package org.braz.sistemaescola.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.braz.sistemaescola.entities.*;
import org.braz.sistemaescola.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

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

    @Autowired
    TurmaDisciplinaRepository turmaDisciplinaRepository;

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    TurmaAlunoRepository turmaAlunoRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;


    @GetMapping("/register/student")
    public String showRegisterAlunoForm(Model model){
        model.addAttribute("aluno", new Aluno());
        model.addAttribute("cursos", cursoRepository.findAll());
        return "pages/registoaluno";
    }

    @ResponseBody
    @GetMapping(value="/register/student/load-turma/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String listTurmasAluno(@PathVariable Integer id) throws JsonProcessingException {
        List<Turma> turmalist = turmaRepository.turmalist(id);

        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(turmalist);
    }

    @PostMapping("/register/student")
    public String addUserAluno(@Valid Aluno aluno, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            model.addAttribute("message", "Registration Failed");
            model.addAttribute("alertClass", "alert-danger");
            model.addAttribute("cursos", cursoRepository.findAll());
            if(aluno.getCurso() != null) {
                model.addAttribute("turmas", turmaRepository.turmalist(aluno.getCurso().getId()));
            }
            return "pages/registoaluno";
        }
        aluno.setPassword(passwordEncoder.encode(aluno.getPassword()));
        usuarioRepository.save(aluno);
        //gerar todas as turmas aluno
        gerarTurmasAluno(aluno.getTurma(), aluno);
        redirectAttributes.addFlashAttribute("message", "Success");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/register";
    }

    private void gerarTurmasAluno(Turma turma, Aluno aluno){
        //Listar todas as turmas disciplinas associadas a uma turma
       List<TurmaDisciplina> turmaDisciplinaList = turmaDisciplinaRepository.turmaDisciplinaList(turma.getId());

       for (TurmaDisciplina turmaDisciplina : turmaDisciplinaList)
       {
           TurmaAluno turmaAluno = new TurmaAluno();
           turmaAluno.setAluno(aluno);
           turmaAluno.setTurmaDisciplina(turmaDisciplina);
           turmaAlunoRepository.save(turmaAluno);
       }
    }

    @GetMapping("/register/professor")
    public String showRegisterProfessorForm(Model model){
        model.addAttribute("professor", new Professor());
        return "pages/registoprofessor";
        }

    @PostMapping("/register/professor")
    public String addUserProfessor(@Valid Professor professor, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            model.addAttribute("message", "Registration Failed");
            model.addAttribute("alertClass", "alert-danger");
            return "pages/registoprofessor";
        }

        usuarioRepository.save(professor);
        redirectAttributes.addFlashAttribute("message", "Success");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/register";
    }

    @GetMapping("/register/subject")
    public String showRegisterSubjectForm(Model model){
        model.addAttribute("disciplina", new Disciplina());
        return "pages/registodisciplina";
    }

    @PostMapping("/register/subject")
    public String addUSubject(@Valid Disciplina disciplina, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            model.addAttribute("message", "Registration Failed");
            model.addAttribute("alertClass", "alert-danger");
            return "pages/registodisciplina";
        }

        disciplinaRepository.save(disciplina);
        redirectAttributes.addFlashAttribute("message", "Success");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/register";
    }

    @GetMapping("/register/course")
    public String showRegisterCourseForm(Model model){
        model.addAttribute("curso", new Curso());
        return "pages/registocurso";
    }

    @PostMapping("/register/course")
    public String addUCourse(@Valid Curso curso, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            model.addAttribute("message", "Registration Failed");
            model.addAttribute("alertClass", "alert-danger");
            return "pages/registocurso";
        }

        cursoRepository.save(curso);
        redirectAttributes.addFlashAttribute("message", "Success");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/register";
    }

    @GetMapping("/register/courseclass")
    public String showRegisterCourseClassForm(Model model){
        model.addAttribute("turma", new Turma());
        model.addAttribute("cursos", cursoRepository.findAll());
        return "pages/registoturma";
    }

    @PostMapping("/register/courseclass")
    public String addUCourseClass(@Valid Turma turma, BindingResult result, Model model, RedirectAttributes redirectAttributes){

        if(result.hasErrors()){
            model.addAttribute("message", "Registration Failed");
            model.addAttribute("alertClass", "alert-danger");
            model.addAttribute("cursos", cursoRepository.findAll());
            return "pages/registoturma";
        }

        turma.setCurso(cursoRepository.findById(turma.getCursoId()).get());
        turmaRepository.save(turma);
        redirectAttributes.addFlashAttribute("message", "Success");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/register";
    }

    @GetMapping("/register/assignregisters")
    public String showAssignRegister(Model model){
        model.addAttribute("turmaDisciplina", new TurmaDisciplina());
        model.addAttribute("cursos", cursoRepository.findAll());
        model.addAttribute("disciplinas", disciplinaRepository.findAll());
        model.addAttribute("professores", professorRepository.findAll());

        return "pages/atribuirregistos";
    }

    @ResponseBody
    @GetMapping(value="/register/assignregisters/load-turma/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String listTurmas(@PathVariable Integer id) throws JsonProcessingException {
        List<Turma> turmalist = turmaRepository.turmalist(id);

        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(turmalist);
    }


    @PostMapping("/register/assignregisters")
    public String addUAssignRegisters(@Valid TurmaDisciplina turmaDisciplina, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            model.addAttribute("message", "Registration Failed");
            model.addAttribute("alertClass", "alert-danger");
            model.addAttribute("cursos", cursoRepository.findAll());

            if(turmaDisciplina.getCursoId() != null) {
                model.addAttribute("turmas", turmaRepository.turmalist(turmaDisciplina.getCursoId()));
            }

            model.addAttribute("disciplinas", disciplinaRepository.findAll());
            model.addAttribute("professores", professorRepository.findAll());
            return "pages/atribuirregistos";
        }

        turmaDisciplinaRepository.save(turmaDisciplina);
        redirectAttributes.addFlashAttribute("message", "Success");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/register";
    }

}
