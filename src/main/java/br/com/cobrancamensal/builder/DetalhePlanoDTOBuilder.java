package br.com.cobrancamensal.builder;

import br.com.cobrancamensal.dto.DetalhePlanoDTO;

public class DetalhePlanoDTOBuilder {

	private String nome;
	private Double valor;

	public DetalhePlanoDTOBuilder nome(String nome) {
		this.nome = nome;
		return this;
	}

	public DetalhePlanoDTOBuilder valor(Double valor) {
		this.valor = valor;
		return this;
	}

	public DetalhePlanoDTO build() {
		DetalhePlanoDTO detalhePlanoDTO = new DetalhePlanoDTO();
		detalhePlanoDTO.setNome(nome);
		detalhePlanoDTO.setValor(valor);
		return detalhePlanoDTO;
	}

}
