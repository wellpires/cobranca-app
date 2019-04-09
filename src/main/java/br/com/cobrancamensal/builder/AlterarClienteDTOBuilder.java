package br.com.cobrancamensal.builder;

import java.time.LocalDate;

import br.com.cobrancamensal.dto.AlterarClienteDTO;
import br.com.cobrancamensal.enums.EstadoCivil;

public class AlterarClienteDTOBuilder {

	private String estadoCivil;
	private LocalDate dataNascimento;

	public AlterarClienteDTOBuilder estadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil.name();
		return this;
	}

	public AlterarClienteDTOBuilder dataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
		return this;
	}

	public AlterarClienteDTO build() {
		AlterarClienteDTO alterarClienteDTO = new AlterarClienteDTO();
		alterarClienteDTO.setEstadoCivil(estadoCivil);
		alterarClienteDTO.setDataNascimento(dataNascimento);
		return alterarClienteDTO;
	}

}
