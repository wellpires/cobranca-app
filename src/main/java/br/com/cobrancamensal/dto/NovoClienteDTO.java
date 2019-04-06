package br.com.cobrancamensal.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.caelum.stella.bean.validation.CPF;
import br.com.cobrancamensal.annotation.EstadoCivil;
import br.com.cobrancamensal.util.JSONLocalDateDeserialize;
import br.com.cobrancamensal.util.JSONLocalDateSerialize;

public class NovoClienteDTO {

	@NotBlank(message = "Nome do cliente é obrigatório!")
	private String nome;

	@JsonDeserialize(using = JSONLocalDateDeserialize.class)
	@JsonSerialize(using = JSONLocalDateSerialize.class)
	private LocalDate dataNascimento;

	@NotBlank(message = "CPF é obrigatório!")
	@CPF(formatted = false, message = "CPF Inválido!")
	private String cpf;

	@EstadoCivil(message = "Estado civil inválido!")
	private String estadoCivil;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEstadoCivil() {
		return this.estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

}
