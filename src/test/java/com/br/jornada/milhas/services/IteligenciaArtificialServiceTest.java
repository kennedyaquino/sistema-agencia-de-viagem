package com.br.jornada.milhas.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class IteligenciaArtificialServiceTest {

    @Autowired
    private IArtificialIteligenteService service;


    @Test
    @DisplayName("Deverá gerar texto descritivo")
    void deveraGerarTextoDescritivo() {
        String msg = "Faça um resumo sobre Nova York enfatizando o porque este lugar é incrível. Utilize uma linguagem informal e até 100 caracteres no máximo em cada parágrafo. Crie 2 parágrafos neste resumo.";

        String result = service.gerarTextoDescritivoDestino(msg);

        System.out.println(result);

        Assertions.assertTrue(result.length() > 0);
    }
}
