package br.com.cobrancamensal.builder;

import java.time.LocalDate;

import br.com.cobrancamensal.dto.NovoClienteDTO;

public class NovoClienteDTOBuilder {

	private String nomeCliente;
	private LocalDate dataNascimento;
	private String cpf;
	private String estadoCivil;

	public NovoClienteDTOBuilder nomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
		return this;
	}

	public NovoClienteDTOBuilder dataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
		return this;
	}

	public NovoClienteDTOBuilder cpf(String cpf) {
		this.cpf = cpf;
		return this;
	}

	public NovoClienteDTOBuilder estadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
		return this;
	}

	public NovoClienteDTO build() {
		NovoClienteDTO novoClienteDTO = new NovoClienteDTO();
		novoClienteDTO.setNome(nomeCliente);
		novoClienteDTO.setDataNascimento(dataNascimento);
		novoClienteDTO.setCpf(cpf);
		novoClienteDTO.setEstadoCivil(estadoCivil);
		return novoClienteDTO;
	}

}
