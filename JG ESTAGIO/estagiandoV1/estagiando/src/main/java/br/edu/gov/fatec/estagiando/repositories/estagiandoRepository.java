package br.edu.gov.fatec.estagiando.repositories;


import br.edu.gov.fatec.estagiando.models.estagiando;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface estagiandoRepository extends JpaRepository<estagiando, Long> {
    List<estagiando> findAllByStatus(boolean status);    
}

