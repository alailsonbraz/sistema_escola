package org.braz.sistemaescola.repository;

import org.braz.sistemaescola.entities.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

    Usuario findByEmailAndPassword(String email, String password);

}
