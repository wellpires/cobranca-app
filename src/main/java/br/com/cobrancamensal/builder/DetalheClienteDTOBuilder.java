package br.com.cobrancamensal.builder;

import java.time.LocalDate;
import java.util.Date;

import br.com.cobrancamensal.dto.DetalheClienteDTO;
import br.com.cobrancamensal.enums.EstadoCivil;
import br.com.cobrancamensal.util.CobrancaUtil;

public class DetalheClienteDTOBuilder {

	private String nomeCliente;
	private String cpf;
	private LocalDate dataNascimento;
	private String estadoCivil;

	public DetalheClienteDTOBuilder nomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
		return this;
	}

	public DetalheClienteDTOBuilder cpf(Long cpf) {
		this.cpf = String.valueOf(cpf);
		return this;
	}

	public DetalheClienteDTOBuilder dataNascimento(Date dataNascimento) {
		this.dataNascimento = CobrancaUtil.dateToLocalDate(dataNascimento);
		return this;
	}

	public DetalheClienteDTOBuilder dataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
		return this;
	}

	public DetalheClienteDTOBuilder estadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil.name();
		return this;
	}

	public DetalheClienteDTO build() {
		DetalheClienteDTO detalheClienteDTO = new DetalheClienteDTO();
		detalheClienteDTO.setNome(nomeCliente);
		detalheClienteDTO.setCpf(cpf);
		detalheClienteDTO.setDataNascimento(dataNascimento);
		detalheClienteDTO.setEstadoCivil(estadoCivil);
		return detalheClienteDTO;
	}

}
