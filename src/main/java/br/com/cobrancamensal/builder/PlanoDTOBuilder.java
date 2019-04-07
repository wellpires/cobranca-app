package br.com.cobrancamensal.builder;

import java.util.ArrayList;
import java.util.List;

import br.com.cobrancamensal.dto.PlanoDTO;

public class PlanoDTOBuilder {

	private String nome;
	private Integer quantidadeItens = 0;

	public PlanoDTOBuilder nome(String nome) {
		this.nome = nome;
		return this;
	}

	public PlanoDTOBuilder quantidadeItens(Integer quantidadeItens) {
		this.quantidadeItens = quantidadeItens;
		return this;
	}

	public PlanoDTO build() {
		PlanoDTO planoDTO = new PlanoDTO();
		planoDTO.setNome(nome);
		return planoDTO;
	}

	public List<PlanoDTO> buildList() {
		List<PlanoDTO> planosDTO = new ArrayList<>();

		for (int i = 0; i < quantidadeItens; i++) {
			planosDTO.add(new PlanoDTO());
		}

		return planosDTO;
	}

}
