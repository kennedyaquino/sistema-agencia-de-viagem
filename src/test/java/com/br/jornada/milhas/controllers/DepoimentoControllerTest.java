package com.br.jornada.milhas.controllers;

import com.br.jornada.milhas.models.Depoimento;
import com.br.jornada.milhas.repositories.DepoimentoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class DepoimentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    DepoimentoRepository repository;

    @Test
    void deveraListarTodosDepoimentos() throws Exception {
        var depoimento = new Depoimento();
        Mockito.when(repository.findAll()).thenReturn(List.of(depoimento));
        this.mockMvc.perform(get("/depoimentos"))
                .andExpect(status().isOk());
    }

    @Test
    void develeraCriarUmDepoimento() throws Exception {
        var depoimento = new Depoimento();
        depoimento.setId("a1234a");
        depoimento.setDepoimento("teste");
        depoimento.setFoto("caminho-para-foto");
        depoimento.setNome("Fulano");

        Mockito.when(repository.save(Mockito.any())).thenReturn(depoimento);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/depoimentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"foto\":\"caminho-para-foto\", \"depoimento\":\"teste\", \"nome\":\"Fulano\" }")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Fulano"))
                .andExpect(content().json("{ id: 'a1234a', foto: 'caminho-para-foto', depoimento: 'teste', nome: 'Fulano' }"));
    }
}

