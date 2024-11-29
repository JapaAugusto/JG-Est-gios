package br.edu.gov.fatec.estagiando.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = UniqueUniversitarioValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUniversitario {
    String message() default "Já existe um Universitario com o mesmo email, cpf, rg ou ra";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
