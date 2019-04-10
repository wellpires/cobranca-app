package br.com.cobrancamensal.builder;

import java.util.ArrayList;
import java.util.List;

import br.com.cobrancamensal.dto.ContratoDTO;

public class ContratoDTOBuilder {

	private Long cpf;
	private String nomePlano;
	private Integer quantidadeItens = 0;

	public ContratoDTOBuilder cpf(Long cpf) {
		this.cpf = cpf;
		return this;
	}

	public ContratoDTOBuilder nomePlano(String nomePlano) {
		this.nomePlano = nomePlano;
		return this;
	}

	public ContratoDTOBuilder quantidadeItens(Integer quantidadeItens) {
		this.quantidadeItens = quantidadeItens;
		return this;
	}

	public ContratoDTO build() {
		ContratoDTO contratoDTO = new ContratoDTO();
		contratoDTO.setCpf(cpf);
		contratoDTO.setNomePlano(nomePlano);
		return contratoDTO;
	}

	public List<ContratoDTO> buildList() {
		List<ContratoDTO> contratosDTO = new ArrayList<>();

		for (int i = 0; i < quantidadeItens; i++) {
			contratosDTO.add(new ContratoDTO());
		}

		return contratosDTO;
	}

}
