package br.com.cobrancamensal.builder;

import java.util.ArrayList;
import java.util.List;

import br.com.cobrancamensal.model.Plano;

public class PlanoBuilder {

	private String nomePlano;
	private Double valor;
	private Integer quantidadeItens;

	public PlanoBuilder nomePlano(String nomePlano) {
		this.nomePlano = nomePlano;
		return this;
	}

	public PlanoBuilder valor(Double valor) {
		this.valor = valor;
		return this;
	}

	public PlanoBuilder quantidadeItens(Integer quantidadeItens) {
		this.quantidadeItens = quantidadeItens;
		return this;
	}

	public Plano build() {
		Plano plano = new Plano();
		plano.setNomePlano(nomePlano);
		plano.setValor(valor);
		return plano;
	}

	public List<Plano> buildList() {
		ArrayList<Plano> planos = new ArrayList<>();

		for (int i = 0; i < quantidadeItens; i++) {
			planos.add(new Plano());
		}

		return planos;
	}

}
