package br.com.cobrancamensal.builder;

import br.com.cobrancamensal.dto.NovoPlanoDTO;

public class NovoPlanoDTOBuilder {

	private String nome;
	private Double valor;

	public NovoPlanoDTOBuilder nome(String nome) {
		this.nome = nome;
		return this;
	}

	public NovoPlanoDTOBuilder valor(Double valor) {
		this.valor = valor;
		return this;
	}

	public NovoPlanoDTO build() {
		NovoPlanoDTO novoPlanoDTO = new NovoPlanoDTO();
		novoPlanoDTO.setNome(nome);
		novoPlanoDTO.setValor(valor);
		return novoPlanoDTO;
	}

}
