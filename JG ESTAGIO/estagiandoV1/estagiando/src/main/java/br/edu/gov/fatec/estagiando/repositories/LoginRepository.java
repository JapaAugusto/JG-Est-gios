package br.edu.gov.fatec.estagiando.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.gov.fatec.estagiando.models.Login;
@Repository
public interface LoginRepository extends JpaRepository<Login, Long>{
    public Login findByUsername(String username);
}
