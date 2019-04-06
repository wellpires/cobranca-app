package br.com.cobrancamensal.builder;

import br.com.cobrancamensal.model.Plano;

public class PlanoBuilder {

	private String nomePlano;
	private Double valor;

	public PlanoBuilder nomePlano(String nomePlano) {
		this.nomePlano = nomePlano;
		return this;
	}

	public PlanoBuilder valor(Double valor) {
		this.valor = valor;
		return this;
	}

	public Plano build() {
		Plano plano = new Plano();
		plano.setNomePlano(nomePlano);
		plano.setValor(valor);
		return plano;
	}

}
