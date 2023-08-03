package com.br.jornada.milhas.services.impl;

import com.br.jornada.milhas.models.Depoimento;
import com.br.jornada.milhas.models.dto.DepoimentoDto;
import com.br.jornada.milhas.models.form.DepoimentoForm;
import com.br.jornada.milhas.repositories.DepoimentoRepository;
import com.br.jornada.milhas.services.IDepoimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class DepoimentoService implements IDepoimentoService {

    @Autowired
    private DepoimentoRepository repository;

    @Override
    public DepoimentoDto criarDepoimento(DepoimentoForm form) {
        return new DepoimentoDto(repository.save(new Depoimento(form)));
    }

    @Override
    public List<DepoimentoDto> listar() {
        return repository.findAll().stream().map(DepoimentoDto::new).collect(Collectors.toList());
    }

    @Override
    public DepoimentoDto atualizarDepoimento(String id, DepoimentoDto depoimentoAtualizado) {
        Depoimento depoimento = repository.findById(id).orElseThrow(() -> new RuntimeException("Não existe id: " + id));

        depoimento.setDepoimento(depoimentoAtualizado.getDepoimento());
        depoimento.setFoto(depoimentoAtualizado.getFoto());
        depoimento.setNome(depoimentoAtualizado.getNome());

        return new DepoimentoDto(repository.save(depoimento));
    }

    @Override
    public boolean deletar(String id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<DepoimentoDto> listarDepoimentosLimitadosEAleatorios() {
        List<Depoimento> depList = repository.findAll();
        List<DepoimentoDto> depoimentos = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            depoimentos.add(new DepoimentoDto(depList.get(new Random().nextInt(depList.size()))));
        }

        return depoimentos;
    }
}
