package com.br.jornada.milhas.models;

import com.br.jornada.milhas.models.form.DepoimentoForm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "depoimentos")
public class Depoimento {

    @Id
    private String id;
    private String foto;
    private String depoimento;
    private String nome;

    public Depoimento(DepoimentoForm form) {
        this.id = UUID.randomUUID().toString();
        this.foto = form.getFoto();
        this.depoimento = form.getDepoimento();
        this.nome = form.getNome();
    }
}
