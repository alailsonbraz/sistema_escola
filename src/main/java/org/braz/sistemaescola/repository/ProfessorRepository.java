package org.braz.sistemaescola.repository;

import org.braz.sistemaescola.entities.Professor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProfessorRepository extends CrudRepository<Professor, Integer> {

    @Query("SELECT COUNT(p) FROM Professor p")
    long countByProfessor();
}
