package br.com.cobrancamensal.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.caelum.stella.bean.validation.CPF;
import br.com.cobrancamensal.annotation.SexoValidation;
import br.com.cobrancamensal.util.JSONLocalDateDeserialize;

public class NovoClienteDTO {

	@NotBlank(message = "Nome do cliente é obrigatório!")
	private String nomeCliente;

	@JsonDeserialize(using = JSONLocalDateDeserialize.class)
	private LocalDate dataNascimento;

	@NotBlank(message = "CPF é obrigatório!")
	@CPF(formatted = false, message = "CPF Inválido!")
	private String cpf;

	@SexoValidation(message = "Sexo inválido!")
	private String sexo;

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
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

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

}
