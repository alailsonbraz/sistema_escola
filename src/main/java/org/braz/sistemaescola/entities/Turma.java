package org.braz.sistemaescola.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Turma {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_turma")
    private Integer id;

    @NotNull(message = "{turno.notempty}")
    @Enumerated(EnumType.STRING)
    private TurnoEnum turno;


    @ManyToOne
    @JoinColumn(name = "id_curso")
    private Curso curso;

    @OneToMany(mappedBy = "turma")
    private List<TurmaDisciplina> turmaDisciplinaList;

    //  Mantem o atributo de forma temporária sem persistir na BD.
    @Transient
    @NotNull(message = "{curso.notempty}")
    private Integer cursoId;

    public Turma() {
    }

    public Turma(Integer id, TurnoEnum turno, Curso curso) {
        this.id = id;
        this.turno = turno;
        this.curso = curso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TurnoEnum getTurno() {
        return turno;
    }

    public void setTurno(TurnoEnum turno) {
        this.turno = turno;
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

    public String getNomeTurma(){
        return id + "." + turno.toString();
    }
}
