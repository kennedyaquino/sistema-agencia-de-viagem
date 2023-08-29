package com.br.jornada.milhas.services;

import com.br.jornada.milhas.models.dto.DestinoDto;
import com.br.jornada.milhas.models.form.DestinoForm;

import java.util.List;

public interface IDestinoService {

    List<DestinoDto> listarTodos(String nome);
    DestinoDto criarDestino(DestinoForm form);
    boolean deletarDestino(String id);
    DestinoDto atualizarDestino(String id, DestinoDto destino);

    DestinoDto buscarDestinoPorId(String id);
}
