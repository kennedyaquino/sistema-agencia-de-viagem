package com.br.jornada.milhas.models;

import com.br.jornada.milhas.models.form.DestinoForm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Destino {

    private String id;
    private String foto;
    private String nome;
    private String preco;

    public Destino(DestinoForm form) {
        this.id = UUID.randomUUID().toString();
        this.foto = form.getFoto();
        this.nome = form.getNome();
        this.preco = form.getPreco();
    }
}
