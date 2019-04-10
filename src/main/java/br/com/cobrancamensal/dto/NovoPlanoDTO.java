package br.com.cobrancamensal.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.cobrancamensal.util.Constantes;

public class NovoPlanoDTO {

	@NotBlank(message = Constantes.CAMPO_NOME_PLANO_OBRIGATORIO)
	@Size(max = 40, message = Constantes.CAMPO_NOME_PLANO_TAMANHO_MAXIMO)
	private String nome;

	@NotNull(message = Constantes.CAMPO_VALOR_OBRIGATORIO)
	@DecimalMax(value = "1000", message = Constantes.CAMPO_VALOR_MAXIMO)
	@DecimalMin(value = "0.05", message = Constantes.CAMPO_VALOR_MINIMO)
	private Double valor;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
