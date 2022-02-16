package org.braz.sistemaescola.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")
public class Administrador extends Usuario{
}
