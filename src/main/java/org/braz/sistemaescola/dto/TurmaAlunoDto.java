package org.braz.sistemaescola.dto;

import org.braz.sistemaescola.entities.TurmaAluno;

import java.util.List;

public class TurmaAlunoDto {

    private List<TurmaAluno> turmas;

    public TurmaAlunoDto() {
    }

    public TurmaAlunoDto(List<TurmaAluno> turmas) {
        this.turmas = turmas;
    }

    public void addTurmaAluno(TurmaAluno turmaAluno){
        this.turmas.add(turmaAluno);
    }

    public List<TurmaAluno> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<TurmaAluno> turmas) {
        this.turmas = turmas;
    }
}
