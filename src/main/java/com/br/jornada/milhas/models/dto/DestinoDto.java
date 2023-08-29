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
    private String foto2;
    private String nome;
    private BigDecimal preco;
    private String meta;
    private String textoDescritivo;

    public DestinoDto(Destino destino) {
        this.id = destino.getId();
        this.foto = destino.getFoto();
        this.nome = destino.getNome();
        this.preco = destino.getPreco();
        this.foto2 = destino.getFoto2();
        this.meta = destino.getMeta();
        this.textoDescritivo = getTextoDescritivo();
    }
}
