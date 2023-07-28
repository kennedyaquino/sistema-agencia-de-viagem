package com.br.jornada.milhas.models.dto;

import com.br.jornada.milhas.models.Depoimento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepoimentoDto {

    private String id;
    private String foto;
    private String depoimento;
    private String nome;

    public DepoimentoDto(Depoimento depoimento) {
        this.id = depoimento.getId();
        this.foto = depoimento.getFoto();
        this.depoimento = depoimento.getDepoimento();
        this.nome = depoimento.getNome();
    }
}
