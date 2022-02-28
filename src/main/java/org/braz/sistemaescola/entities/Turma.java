package org.braz.sistemaescola.entities;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Turma {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_turma")
    private Integer id;
    @Enumerated(EnumType.STRING)
    private TurnoEnum turno;
    @ManyToOne
    @JoinColumn(name = "id_curso")
    private Curso curso;

    public Curso getCurso() {
        return curso;
    }

    public Turma() {
    }

    public Turma(Integer id, TurnoEnum turno) {
        this.id = id;
        this.turno = turno;
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
}
