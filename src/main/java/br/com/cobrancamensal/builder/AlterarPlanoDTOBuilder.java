package br.com.cobrancamensal.builder;

import br.com.cobrancamensal.dto.AlterarPlanoDTO;

public class AlterarPlanoDTOBuilder {

	private Double valor;

	public AlterarPlanoDTOBuilder valor(Double valor) {
		this.valor = valor;
		return this;
	}

	public AlterarPlanoDTO build() {
		AlterarPlanoDTO alterarPlanoDTO = new AlterarPlanoDTO();
		alterarPlanoDTO.setValor(valor);
		return alterarPlanoDTO;
	}

}
