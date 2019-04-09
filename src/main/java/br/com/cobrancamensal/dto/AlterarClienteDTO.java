package br.com.cobrancamensal.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.cobrancamensal.util.JSONLocalDateDeserialize;

public class AlterarClienteDTO {

	private String nome;
	private String cpf;

	@JsonDeserialize(using = JSONLocalDateDeserialize.class)
	private LocalDate dataNascimento;
	private String estadoCivil;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

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
