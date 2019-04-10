package br.com.cobrancamensal.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.cobrancamensal.util.JSONLocalDateDeserialize;
import br.com.cobrancamensal.util.JSONLocalDateSerialize;

public class DetalheContratoDTO {

	private Long cpf;
	private String nomePlano;
	@JsonSerialize(using = JSONLocalDateSerialize.class)
	@JsonDeserialize(using = JSONLocalDateDeserialize.class)
	private LocalDate dataContratacao;

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public String getNomePlano() {
		return nomePlano;
	}

	public void setNomePlano(String nomePlano) {
		this.nomePlano = nomePlano;
	}

	public LocalDate getDataContratacao() {
		return dataContratacao;
	}

	public void setDataContratacao(LocalDate dataContratacao) {
		this.dataContratacao = dataContratacao;
	}

}
