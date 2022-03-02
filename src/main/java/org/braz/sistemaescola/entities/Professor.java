package org.braz.sistemaescola.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue("P")
public class Professor extends Usuario{

    @OneToMany(mappedBy = "professor")
    private List<TurmaDisciplina> turmaDisciplinaList;

    public Professor() {
    }

    public Professor(Integer id, String nome, String email, Date data_nascimento, GeneroEnum genero, String password) {
        super(id, nome, email, data_nascimento, genero, password);
    }
}
