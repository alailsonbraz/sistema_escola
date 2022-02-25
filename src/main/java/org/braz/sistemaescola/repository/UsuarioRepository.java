package org.braz.sistemaescola.repository;

import org.braz.sistemaescola.entities.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

    Usuario findByEmailAndPassword(String email, String password);

    @Query("SELECT COUNT(a) FROM Aluno a")
    long countByAluno();

    @Query("SELECT COUNT(p) FROM Professor p")
    long countByProfessor();

}
