package com.br.jornada.milhas.repositories;

import com.br.jornada.milhas.models.Depoimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepoimentoRepository extends JpaRepository<Depoimento, String> {
}
