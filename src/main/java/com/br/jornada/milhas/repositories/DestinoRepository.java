package com.br.jornada.milhas.repositories;

import com.br.jornada.milhas.models.Destino;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DestinoRepository extends JpaRepository<Destino, String> {

    List<Destino> findAllByNome(String nome);
}
