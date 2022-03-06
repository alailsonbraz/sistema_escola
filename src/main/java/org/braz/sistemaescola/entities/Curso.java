package org.braz.sistemaescola.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Curso implements Serializable {
    private static final long serialVersionUID = -3988513983361180058L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_curso")
    private Integer id;

    @NotNull
    @NotEmpty(message = "{nome.notempty}")
    private String nome;
    @OneToMany(mappedBy = "curso")
    private List<Turma> turmaList;

    public Curso() {
    }

    public Curso(Integer id, String nome) {
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
