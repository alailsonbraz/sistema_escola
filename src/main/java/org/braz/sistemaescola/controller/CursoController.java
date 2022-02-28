package org.braz.sistemaescola.controller;

import org.braz.sistemaescola.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CursoController {
    @Autowired
    CursoRepository cursoRepository;


}
