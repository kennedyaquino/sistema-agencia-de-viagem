package com.br.jornada.milhas.models.form;

import jakarta.validation.constraints.Max;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
