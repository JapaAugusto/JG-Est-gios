package br.edu.gov.fatec.estagiando.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.gov.fatec.estagiando.models.Universitario;

@Repository
public interface UniversitarioRepository extends JpaRepository<Universitario, Long> {
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
    boolean existsByRg(String rg);
    boolean existsByRa(String ra);
    Optional<Universitario> findByEmailOrCpfOrRgOrRa(String email, String cpf, String rg, String ra);
    List<Universitario> findAllByStatus(boolean status); 
}


