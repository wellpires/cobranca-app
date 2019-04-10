package br.com.cobrancamensal.builder;

import java.time.LocalDate;
import java.util.Date;

import br.com.cobrancamensal.dto.DetalheContratoDTO;
import br.com.cobrancamensal.util.CobrancaUtil;

public class DetalheContratoDTOBuilder {

	private Long cpf;
	private String nomePlano;
	private LocalDate dataContratacao;

	public DetalheContratoDTOBuilder cpf(Long cpf) {
		this.cpf = cpf;
		return this;
	}

	public DetalheContratoDTOBuilder nomePlano(String nomePlano) {
		this.nomePlano = nomePlano;
		return this;
	}

	public DetalheContratoDTOBuilder dataContratacao(Date dataContratacao) {
		this.dataContratacao = CobrancaUtil.dateToLocalDate(dataContratacao);
		return this;
	}

	public DetalheContratoDTOBuilder dataContratacao(LocalDate dataContratacao) {
		this.dataContratacao = dataContratacao;
		return this;
	}

	public DetalheContratoDTO build() {
		DetalheContratoDTO detalheContratoDTO = new DetalheContratoDTO();
		detalheContratoDTO.setCpf(cpf);
		detalheContratoDTO.setNomePlano(nomePlano);
		detalheContratoDTO.setDataContratacao(dataContratacao);
		return detalheContratoDTO;
	}

}
