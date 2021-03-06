package org.braz.sistemaescola.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "usuario_tipo",
    discriminatorType = DiscriminatorType.STRING)
    public class Usuario implements Serializable {

    private static final long serialVersionUID = 2123431476248977308L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_usuario")
    private Integer id;

    @NotNull
    @NotEmpty(message = "{nome.notempty}")
    private String nome;

    @NotNull
    @NotEmpty(message = "{email.notempty}")
    private String email;

    @NotNull(message = "{data.notempty}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date data_nascimento;

    @NotNull(message = "{genero.notempty}")
    @Enumerated(EnumType.STRING)
    private GeneroEnum genero;

    @NotNull(message = "{password.notempty}")
    @NotEmpty(message = "{password.notempty}")
    private String password;

    public Usuario() {
    }

    public Usuario(Integer id, String nome, String email, Date data_nascimento, GeneroEnum genero, String password) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.data_nascimento = data_nascimento;
        this.genero = genero;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public GeneroEnum getGenero() {
        return genero;
    }

    public void setGenero(GeneroEnum genero) {
        this.genero = genero;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Transient
    public String getDiscriminatorValue(){
        DiscriminatorValue val = this.getClass().getAnnotation( DiscriminatorValue.class );

        return val == null ? null : val.value();
    }
}
