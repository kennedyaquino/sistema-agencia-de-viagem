package com.br.jornada.milhas.models.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;

@NoArgsConstructor
@Getter
@Setter
public class DestinoForm {

    private String foto;
    private String foto2;
    private String nome;
    private String preco;
    @Max(value = 160)
    private String meta;
    private String textoDescritivo;

}
