package br.com.cobrancamensal.validator;

import java.time.LocalDate;
import java.time.Period;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.cobrancamensal.annotation.MaiorIgualDezoitoAnos;

public class MaiorIgualDezoitoAnosValidator implements ConstraintValidator<MaiorIgualDezoitoAnos, LocalDate> {

	@Override
	public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
		return Period.between(value, LocalDate.now()).getYears() >= 18;
	}

}
