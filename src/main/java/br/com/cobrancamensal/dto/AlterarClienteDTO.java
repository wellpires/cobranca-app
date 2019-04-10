package br.com.cobrancamensal.dto;

import java.time.LocalDate;

import javax.validation.constraints.Past;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.cobrancamensal.annotation.EstadoCivil;
import br.com.cobrancamensal.annotation.MaiorIgualDezoitoAnos;
import br.com.cobrancamensal.util.Constantes;
import br.com.cobrancamensal.util.JSONLocalDateDeserialize;
import br.com.cobrancamensal.util.JSONLocalDateSerialize;

public class AlterarClienteDTO {

	@JsonDeserialize(using = JSONLocalDateDeserialize.class)
	@JsonSerialize(using = JSONLocalDateSerialize.class)
	@Past(message = Constantes.CAMPO_DATA_NASCIMENTO_DEVE_SER_ANTERIOR_HOJE)
	@MaiorIgualDezoitoAnos(message = Constantes.CAMPO_CLIENTE_MAIOR_DE_IDADE)
	private LocalDate dataNascimento;

	@EstadoCivil(message = Constantes.CAMPO_ESTADO_CIVIL_INVALIDO)
	private String estadoCivil;

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

}
