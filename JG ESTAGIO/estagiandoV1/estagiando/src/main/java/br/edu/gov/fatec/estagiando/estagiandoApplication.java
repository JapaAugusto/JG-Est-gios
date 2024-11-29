package br.edu.gov.fatec.estagiando;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("br.edu.gov.fatec.estagiando")
public class estagiandoApplication {

	public static void main(String[] args) {
		SpringApplication.run(estagiandoApplication.class, args);
	}

}
