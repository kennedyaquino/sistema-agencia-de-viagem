package com.br.jornada.milhas.controllers;

import com.br.jornada.milhas.models.dto.DepoimentoDto;
import com.br.jornada.milhas.models.form.DepoimentoForm;
import com.br.jornada.milhas.services.IDepoimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/depoimentos")
public class DepoimentoController {

    @Autowired
    private IDepoimentoService service;

    @GetMapping
    public ResponseEntity<List<DepoimentoDto>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/depoimentos-home")
    public ResponseEntity<List<DepoimentoDto>> listarDepoimentosLimitadosEAleatorios() {
        return ResponseEntity.ok(service.listarDepoimentosLimitadosEAleatorios());
    }

    @PostMapping
    public ResponseEntity<DepoimentoDto> criar(@RequestBody DepoimentoForm  form) {
        DepoimentoDto depoimento = service.criarDepoimento(form);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(depoimento.getId()).toUri();
        return ResponseEntity.created(uri).body(depoimento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepoimentoDto> atualizar(@RequestBody DepoimentoDto depoimento, @PathVariable String id) {
        return ResponseEntity.ok(service.atualizarDepoimento(id, depoimento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        if(service.deletar(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
