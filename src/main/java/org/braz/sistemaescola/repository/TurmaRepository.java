package org.braz.sistemaescola.repository;

import org.braz.sistemaescola.entities.Turma;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TurmaRepository extends CrudRepository<Turma, Integer> {

    Turma findById(int id);

    @Query("SELECT COUNT(t) FROM Turma t")
    long countByTurma();
}
