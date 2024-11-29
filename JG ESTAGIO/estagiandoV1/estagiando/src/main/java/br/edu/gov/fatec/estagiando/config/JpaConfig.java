package br.edu.gov.fatec.estagiando.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "br.edu.gov.fatec.estagiando.repositories")
public class JpaConfig {

}
