package com.br.jornada.milhas.models;

import com.br.jornada.milhas.models.form.DestinoForm;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "destinos")
public class Destino {

    @Id
    private String id;
    private String foto;
    private String nome;
    private BigDecimal preco;

    public Destino(DestinoForm form) {
        this.id = UUID.randomUUID().toString();
        this.foto = form.getFoto();
        this.nome = form.getNome();
        this.preco = new BigDecimal(form.getPreco());
    }
}
