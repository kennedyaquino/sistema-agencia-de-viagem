package com.br.jornada.milhas.models.dto;

import com.br.jornada.milhas.models.Destino;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DestinoDto {

    private String id;
    private String foto;
    private String nome;
    private BigDecimal preco;

    public DestinoDto(Destino destino) {
        this.id = destino.getId();
        this.foto = destino.getFoto();
        this.nome = destino.getNome();
        this.preco = destino.getPreco();
    }
}
