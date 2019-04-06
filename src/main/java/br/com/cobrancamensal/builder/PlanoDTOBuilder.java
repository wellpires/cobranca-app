package br.com.cobrancamensal.builder;

import br.com.cobrancamensal.dto.PlanoDTO;

public class PlanoDTOBuilder {

	private Long id;
	private String nome;

	public PlanoDTOBuilder id(Long id) {
		this.id = id;
		return this;
	}

	public PlanoDTOBuilder nome(String nome) {
		this.nome = nome;
		return this;
	}

	public PlanoDTO build() {
		PlanoDTO planoDTO = new PlanoDTO();
		planoDTO.setId(id);
		planoDTO.setNome(nome);
		return planoDTO;
	}

}
