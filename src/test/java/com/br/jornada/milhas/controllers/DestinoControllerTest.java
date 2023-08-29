package com.br.jornada.milhas.controllers;

import com.br.jornada.milhas.models.dto.DestinoDto;
import com.br.jornada.milhas.models.form.DestinoForm;
import com.br.jornada.milhas.services.IDestinoService;
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

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class DestinoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private IDestinoService service;

    @Test
    @DisplayName("Deverá ter status 200(Ok) ao listar todos os destinos")
    void deveraListarTodosDestinos() throws Exception {
        Mockito.when(service.listarTodos("")).thenReturn(List.of(new DestinoDto()));
        this.mockMvc.perform(get("/destinos"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deverá ter status 200(Ok) ao listar todos os destinos que contém o nome passado como parametro")
    void deveraListarTodosDestinosComParamentroNome() throws Exception {
        String nome = "São Paulo";
        Mockito.when(service.listarTodos(nome)).thenReturn(List.of(new DestinoDto()));
        this.mockMvc.perform(get("/destinos?nome={nome}", nome))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deverá ter status 203(CREATED) ao criar um destino")
    void deverarCriarDestino() throws Exception {

        var destino = new DestinoDto();
        destino.setId(UUID.randomUUID().toString());
        destino.setFoto("caminho-foto");
        destino.setNome("USA");
        destino.setPreco(new BigDecimal("5000"));

        var form  = new DestinoForm();
        form.setFoto(destino.getFoto());
        form.setPreco(destino.getPreco().toString());
        form.setNome(destino.getNome());

        Mockito.when(service.criarDestino(Mockito.any())).thenReturn(destino);

        this.mockMvc.perform(post("/destinos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(form))
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(destino.getId()))
                .andExpect(content().json(mapper.writeValueAsString(destino)));
    }

    @Test
    @DisplayName("Deverá ter status 200(OK) ao atualizar um destino por id")
    void deveraAtualizarDestinoPorId() throws Exception {
        String id = UUID.randomUUID().toString();

        var destino = new DestinoDto();
        destino.setId(id);
        destino.setFoto("caminho-foto");
        destino.setNome("USA");
        destino.setPreco(new BigDecimal("5000"));

        Mockito.when(service.atualizarDestino(id, new DestinoDto())).thenReturn(destino);

        this.mockMvc.perform(put("/destinos/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(destino))
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("Deverá ter status 404(NOT FOUND) ao deletar um destino com id inexistente")
    void naoDeveraDeletarDestinoComIdInexistente() throws Exception {
        String id = UUID.randomUUID().toString();

        Mockito.when(service.deletarDestino(id)).thenReturn(false);

        this.mockMvc.perform(delete("/destinos/{id}", id))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Deverá ter status 200(OK) ao buscar um destino por id")
    void deveraBuscarDestinoPorId() throws Exception {
        String id = UUID.randomUUID().toString();

        var destino = new DestinoDto();
        destino.setId(id);
        destino.setFoto("caminho-foto");
        destino.setNome("USA");
        destino.setPreco(new BigDecimal("5000"));
        destino.setMeta("Subtitulo subtitulo");
        destino.setFoto2("caminho-foto-2");
        destino.setTextoDescritivo("");


        Mockito.when(service.buscarDestinoPorId(id)).thenReturn(destino);

        this.mockMvc.perform(get("/destinos/{id}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(destino)));
    }


}
