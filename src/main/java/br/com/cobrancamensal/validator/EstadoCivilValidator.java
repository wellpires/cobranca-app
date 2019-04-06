package br.com.cobrancamensal.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.cobrancamensal.annotation.EstadoCivil;

public class EstadoCivilValidator implements ConstraintValidator<EstadoCivil, String> {

	@Override
	public boolean isValid(String estadoCivil, ConstraintValidatorContext context) {
		return br.com.cobrancamensal.enums.EstadoCivil.exists(estadoCivil);
	}

}
