package org.braz.sistemaescola.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@DiscriminatorValue("A")
public class Aluno extends Usuario{

    private static final long serialVersionUID = -7599079571656437685L;
    @ManyToOne
    @JoinColumn(name = "id_curso")
    private Curso curso;
    //Mantem o atributo de forma temporária sem persistir na BD.
    @Transient
    private Integer cursoId;

    public Aluno() {
    }

    public Aluno(Integer id, String nome, String email, Date data_nascimento, GeneroEnum genero, String password, Curso curso) {
        super(id, nome, email, data_nascimento, genero, password);
        this.curso=curso;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Integer getCursoId() {
        return cursoId;
    }

    public void setCursoId(Integer cursoId) {
        this.cursoId = cursoId;
    }
}
