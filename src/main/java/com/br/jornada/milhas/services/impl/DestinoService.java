package com.br.jornada.milhas.services.impl;

import com.br.jornada.milhas.exceptions.NotFoundException;
import com.br.jornada.milhas.models.Destino;
import com.br.jornada.milhas.models.dto.DestinoDto;
import com.br.jornada.milhas.models.form.DestinoForm;
import com.br.jornada.milhas.repositories.DestinoRepository;
import com.br.jornada.milhas.services.IArtificialIteligenteService;
import com.br.jornada.milhas.services.IDestinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DestinoService implements IDestinoService {

    @Autowired
    private DestinoRepository repository;

    @Autowired
    private IArtificialIteligenteService iaService;

    @Override
    public List<DestinoDto> listarTodos(String nome) {
        if (nome.isEmpty()) {
            return repository.findAll().stream().map(destino -> new DestinoDto(destino)).collect(Collectors.toList());
        }

        List<DestinoDto> destinos = repository.findAllByNome(nome).stream().map(destino -> new DestinoDto(destino)).collect(Collectors.toList());

        if (destinos.isEmpty()) {
            throw new NotFoundException("Nenhum destino foi encontrado");
        }

        return destinos;
    }

    @Override
    public DestinoDto criarDestino(DestinoForm form) {
        if(form.getTextoDescritivo().isEmpty()) {
            String texto = "Faça um resumo sobre "+ form.getNome() +" enfatizando o porque este lugar é incrível. Utilize uma linguagem informal e até 100 caracteres no máximo em cada parágrafo. Crie 2 parágrafos neste resumo.";

            form.setTextoDescritivo(iaService.gerarTextoDescritivoDestino(texto));
        }

        Destino destino = new Destino(form);
        return new DestinoDto(repository.save(destino));
    }

    @Override
    public boolean deletarDestino(String id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public DestinoDto atualizarDestino(String id, DestinoDto destinoAtualizado) {
        Destino destino = repository.findById(id).orElseThrow(() -> new NotFoundException("Não existe id: " + id));

        destino.setFoto(destinoAtualizado.getFoto());
        destino.setPreco(destinoAtualizado.getPreco());
        destino.setNome(destinoAtualizado.getNome());

        return new DestinoDto(repository.save(destino));
    }

    @Override
    public DestinoDto buscarDestinoPorId(String id) {
        return new DestinoDto(repository.findById(id).orElseThrow(() -> new NotFoundException("Nenhum destino foi encontrado")));
    }
}
