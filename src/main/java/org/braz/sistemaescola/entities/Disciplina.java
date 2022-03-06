package org.braz.sistemaescola.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Disciplina {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_disciplina")
    private Integer id;

    @NotNull
    @NotEmpty(message = "{nome.notempty}")
    private String nome;

    @OneToMany(mappedBy = "disciplina")
    private List<TurmaDisciplina> turmaDisciplinaList;

    public Disciplina() {
    }

    public Disciplina(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
