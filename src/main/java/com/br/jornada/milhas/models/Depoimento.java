package com.br.jornada.milhas.models;

import com.br.jornada.milhas.models.form.DepoimentoForm;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
