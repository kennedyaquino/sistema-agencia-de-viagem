package com.br.jornada.milhas.models;

import com.br.jornada.milhas.models.form.DestinoForm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
    private String foto2;
    private String meta;
    private String nome;
    private BigDecimal preco;
    @Column(name = "texto_descritivo")
    private String textoDescritivo;


    public Destino(DestinoForm form) {
        this.id = UUID.randomUUID().toString();
        this.foto = form.getFoto();
        this.nome = form.getNome();
        this.preco = new BigDecimal(form.getPreco());
        this.meta = form.getMeta();
        this.textoDescritivo = form.getTextoDescritivo();
    }
}
