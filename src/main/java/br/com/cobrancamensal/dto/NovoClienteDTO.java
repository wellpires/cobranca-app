package br.com.cobrancamensal.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.caelum.stella.bean.validation.CPF;
import br.com.cobrancamensal.annotation.EstadoCivil;
import br.com.cobrancamensal.util.Constantes;
import br.com.cobrancamensal.util.JSONLocalDateDeserialize;
import br.com.cobrancamensal.util.JSONLocalDateSerialize;

public class NovoClienteDTO {

	@NotBlank(message = Constantes.CAMPO_NOME_CLIENTE_OBRIGATORIO)
	@Size(max = 100, message = Constantes.CAMPO_NOME_CLIENTE_TAMANHO_MAXIMO)
	private String nome;

	@JsonDeserialize(using = JSONLocalDateDeserialize.class)
	@JsonSerialize(using = JSONLocalDateSerialize.class)
	private LocalDate dataNascimento;

	@NotBlank(message = Constantes.CAMPO_CPF_OBRIGATORIO)
	@CPF(formatted = false, message = Constantes.CAMPO_CPF_INVALIDO)
	private String cpf;

	@EstadoCivil(message = Constantes.CAMPO_ESTADO_CIVIL_INVALIDO)
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
