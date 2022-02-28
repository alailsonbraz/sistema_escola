package org.braz.sistemaescola.repository;

import org.braz.sistemaescola.entities.Disciplina;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DisciplinaRepository extends CrudRepository<Disciplina, Integer> {

    Disciplina findById(int id);

    @Query("SELECT COUNT(d) FROM Disciplina d")
    long countByDisciplina();
}
