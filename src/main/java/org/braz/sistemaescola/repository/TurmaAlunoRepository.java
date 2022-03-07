package org.braz.sistemaescola.repository;

import org.braz.sistemaescola.entities.TurmaAluno;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TurmaAlunoRepository extends CrudRepository<TurmaAluno, Integer> {

    @Query("SELECT T FROM TurmaAluno T JOIN T.aluno A WHERE A.email= ?1")
    List<TurmaAluno> turmaAlunoList(String email);



}
