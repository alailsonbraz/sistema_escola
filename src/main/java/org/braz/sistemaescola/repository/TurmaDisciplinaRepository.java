package org.braz.sistemaescola.repository;

import org.braz.sistemaescola.entities.TurmaDisciplina;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TurmaDisciplinaRepository extends CrudRepository<TurmaDisciplina, Integer> {

    @Query("SELECT td FROM TurmaDisciplina td WHERE td.turma.id= ?1")
    List<TurmaDisciplina> turmaDisciplinaList(Integer idTurmaDisciplina);


}
