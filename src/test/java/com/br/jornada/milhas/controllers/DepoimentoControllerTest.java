package com.br.jornada.milhas.controllers;

import com.br.jornada.milhas.models.dto.DepoimentoDto;
import com.br.jornada.milhas.services.IDepoimentoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class DepoimentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private IDepoimentoService service;
    
    @Test
    @DisplayName("Deverá ter status 200(OK) ao listar todos os depoimentos")
    void deveraListarTodosDepoimentos() throws Exception {
        Mockito.when(service.listar()).thenReturn(List.of(new DepoimentoDto()));
        this.mockMvc.perform(get("/depoimentos"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deverá ter status 201(CREATED) ao criar um depoimento")
    void develeraCriarUmDepoimento() throws Exception {
        var depoimento = new DepoimentoDto();
        depoimento.setId("a1234a");
        depoimento.setDepoimento("teste");
        depoimento.setFoto("caminho-para-foto");
        depoimento.setNome("Fulano");

        Mockito.when(service.criarDepoimento(Mockito.any())).thenReturn(depoimento);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/depoimentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"foto\":\"caminho-para-foto\", \"depoimento\":\"teste\", \"nome\":\"Fulano\" }")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Fulano"))
                .andExpect(content().json(mapper.writeValueAsString(depoimento)));
    }

    @Test
    @DisplayName("Deverá ter status 200(OK) ao deletar um depoimento por id")
    void deverarDeletarDepoimentoPorId() throws Exception {
        Mockito.when(service.deletar("123")).thenReturn(true);

        this.mockMvc.perform(delete("/depoimentos/123"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deverá ter status 404(NOT_FOUND) ao deletar depoimento com id inexistente")
    void naoDeveraDeletarDepoimentoComIdInexistente() throws Exception {
        String id = "123";

        Mockito.when(service.deletar(id)).thenReturn(false);

        this.mockMvc.perform(delete("/depoimentos/{id}", "123"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Deverá ter status 200(OK) ao atualizar um depoimento por id")
    void deveraAtualizarDepoimentoPorId() throws Exception {
        var depoimento = new DepoimentoDto();
        depoimento.setId("123");
        depoimento.setDepoimento("teste");
        depoimento.setFoto("caminho-para-foto");
        depoimento.setNome("Fulano");

        Mockito.when(service.atualizarDepoimento("", new DepoimentoDto())).thenReturn(depoimento);

        this.mockMvc.perform(put("/depoimentos/{id}", depoimento.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(depoimento))
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }
}

