package org.braz.sistemaescola.repository;

import org.braz.sistemaescola.entities.Turma;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TurmaRepository extends CrudRepository<Turma, Integer> {

    Turma findById(int id);

    @Query("SELECT COUNT(t) FROM Turma t")
    long countByTurma();

    @Query("SELECT t FROM Turma t WHERE t.curso.id = ?1")
    List<Turma> turmalist(Integer idCurso);
}
