package org.braz.sistemaescola.entities;
import java.sql.Date;

public class Aluno extends Usuario{

    public Aluno() {
    }

    public Aluno(Integer id, String nome, String email, Date data_nascimento, GeneroEnum genero, String password) {
        super(id, nome, email, data_nascimento, genero, password);
    }
}
