package org.braz.sistemaescola.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class TurmaDisciplina {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_turma_disciplina")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_professor")
    @NotNull(message = "{professor.notempty}")
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "id_disciplina")
    @NotNull(message = "{disciplina.notempty}")
    private Disciplina disciplina;

    @ManyToOne
    @JoinColumn(name = "id_turma")
    @NotNull(message = "{turma.notempty}")
    private Turma turma;

    //  Mantem o atributo de forma temporária sem persistir na BD.
    @Transient
    @NotNull(message = "{curso.notempty}")
    private Integer cursoId;

    public TurmaDisciplina() {
    }

    public TurmaDisciplina(Integer id) {
        this.id = id;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Integer getCursoId() {
        return cursoId;
    }

    public void setCursoId(Integer cursoId) {
        this.cursoId = cursoId;
    }
}
