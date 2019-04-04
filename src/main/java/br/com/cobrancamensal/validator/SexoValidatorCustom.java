package br.com.cobrancamensal.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.cobrancamensal.annotation.SexoValidation;
import br.com.cobrancamensal.enums.Sexo;

public class SexoValidatorCustom implements ConstraintValidator<SexoValidation, String> {

	@Override
	public boolean isValid(String sexo, ConstraintValidatorContext context) {
		return Sexo.exists(sexo);
	}

}
