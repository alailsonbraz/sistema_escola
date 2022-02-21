package org.braz.sistemaescola.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("A")
public class Aluno extends Usuario{

    public Aluno() {
    }

    public Aluno(Integer id, String nome, String email, Date data_nascimento, GeneroEnum genero, String password) {
        super(id, nome, email, data_nascimento, genero, password);
    }
}
