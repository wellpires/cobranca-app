package br.com.cobrancamensal.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.cobrancamensal.validator.EstadoCivilValidator;

@Retention(RUNTIME)
@Target({ FIELD, METHOD })
@Constraint(validatedBy = EstadoCivilValidator.class)
public @interface EstadoCivil {
	String message();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
