package org.braz.sistemaescola.entities;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class TurmaAluno {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_turma_aluno")
    private Integer id;


    @ManyToOne
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;


    private Double nota;


    @ManyToOne
    @JoinColumn(name = "id_turma_disciplina")
    private TurmaDisciplina turmaDisciplina;

    public TurmaAluno() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public TurmaDisciplina getTurmaDisciplina() {
        return turmaDisciplina;
    }

    public void setTurmaDisciplina(TurmaDisciplina turmaDisciplina) {
        this.turmaDisciplina = turmaDisciplina;
    }

    public String getStatus(){
        if(nota != null){
            if(nota >= 10){
                return "Aprovado";
            }else{
                return "Reprovado";
            }
        }else{
            return "Cursando";
        }
    }
}
