package br.edu.gov.fatec.estagiando.validations;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.gov.fatec.estagiando.models.Universitario;
import br.edu.gov.fatec.estagiando.repositories.UniversitarioRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueUniversitarioValidator implements ConstraintValidator<UniqueUniversitario, Universitario> {

    @Autowired
    private UniversitarioRepository UniversitarioRepository;

    @Override
    public void initialize(UniqueUniversitario constraintAnnotation) {
    }

    @Override
    public boolean isValid(Universitario Universitario, ConstraintValidatorContext context) {
        if (UniversitarioRepository.existsByEmail(Universitario.getEmail()) ||
            UniversitarioRepository.existsByCpf(Universitario.getCpf()) ||
            UniversitarioRepository.existsByRg(Universitario.getRg()) ||
            UniversitarioRepository.existsByRa(Universitario.getRa())) {
            return false;
        }
        return true;
    }
}
