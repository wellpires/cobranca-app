package br.com.cobrancamensal.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;

import br.com.cobrancamensal.util.Constantes;

public class AlterarPlanoDTO {

	@NotNull(message = Constantes.CAMPO_VALOR_OBRIGATORIO)
	@DecimalMax(value = "1000", message = Constantes.CAMPO_VALOR_MAXIMO)
	private Double valor;

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
