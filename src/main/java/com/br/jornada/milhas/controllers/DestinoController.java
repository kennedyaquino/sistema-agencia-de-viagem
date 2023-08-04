package com.br.jornada.milhas.controllers;

import com.br.jornada.milhas.models.dto.DestinoDto;
import com.br.jornada.milhas.models.form.DestinoForm;
import com.br.jornada.milhas.services.IDestinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/destinos")
public class DestinoController {

    @Autowired
    private IDestinoService service;

    @GetMapping
    public ResponseEntity<List<DestinoDto>> listarTodos(
            @RequestParam(value = "nome", defaultValue = "") String nome) {

        return ResponseEntity.ok(service.listarTodos(nome));
    }

    @PostMapping
    public ResponseEntity<DestinoDto> criarDestino(@RequestBody DestinoForm form) {
        DestinoDto destino = service.criarDestino(form);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(destino.getId()).toUri();
        return ResponseEntity.created(uri).body(destino);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DestinoDto> atualizarDestino(@PathVariable String id, @RequestBody DestinoDto destino) {
        return ResponseEntity.ok(service.atualizarDestino(id, destino));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDestino(@PathVariable String id) {
        boolean status = service.deletarDestino(id);

        if (status) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
