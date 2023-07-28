package com.br.jornada.milhas.services;


import com.br.jornada.milhas.models.dto.DepoimentoDto;
import com.br.jornada.milhas.models.form.DepoimentoForm;

import java.util.List;

public interface IDepoimentoService {

    DepoimentoDto criarDepoimento(DepoimentoForm form);
    List<DepoimentoDto> listar();
    DepoimentoDto atualizarDepoimento(String id, DepoimentoDto depoimentoAtualizado);
    void deletar(String id);

    List<DepoimentoDto> listarDepoimentosLimitadosEAleatorios();

}
