package org.braz.sistemaescola.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@DiscriminatorValue("A")
public class Aluno extends Usuario{

    private static final long serialVersionUID = -7599079571656437685L;

    @ManyToOne
    @JoinColumn(name = "id_curso")
    @NotNull(message = "{curso.notempty}")
    private Curso curso;

    @NotNull(message = "{turma.notempty}")
    @Transient
    private Turma turma;

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

//    pub

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }
}
