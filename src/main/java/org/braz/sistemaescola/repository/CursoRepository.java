package org.braz.sistemaescola.repository;

import org.braz.sistemaescola.entities.Curso;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CursoRepository extends CrudRepository<Curso, Integer> {

    Curso findById(int id);

    @Query("SELECT COUNT(c) FROM Curso c")
    long countByCurso();
}
