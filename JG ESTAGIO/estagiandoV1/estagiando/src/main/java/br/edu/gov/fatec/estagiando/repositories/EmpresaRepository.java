package br.edu.gov.fatec.estagiando.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.gov.fatec.estagiando.models.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    List<Empresa> findAllByStatus(boolean status);    
}
