package br.com.cobrancamensal.builder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.cobrancamensal.model.Contrato;
import br.com.cobrancamensal.model.pk.ContratoPK;

public class ContratoBuilder {

	private Integer quantidadeItens = 0;
	private String cpf;
	private String nomePlano;
	private Date dataContratacao;

	public ContratoBuilder quantidadeItens(Integer quantidadeItens) {
		this.quantidadeItens = quantidadeItens;
		return this;
	}

	public ContratoBuilder cpf(Long cpf) {
		this.cpf = String.valueOf(cpf);
		return this;
	}

	public ContratoBuilder nomePlano(String nomePlano) {
		this.nomePlano = nomePlano;
		return this;
	}

	public ContratoBuilder dataContratacao(Date dataContratacao) {
		this.dataContratacao = dataContratacao;
		return this;
	}

	public List<Contrato> buildList() {
		List<Contrato> contratos = new ArrayList<>();

		for (int i = 0; i < quantidadeItens; i++) {
			Contrato contrato = new Contrato();
			contrato.setIdContrato(new ContratoPK("12345678901", String.format("Plano teste %d", i)));
			contratos.add(contrato);
		}

		return contratos;
	}

	public Contrato build() {
		Contrato contrato = new Contrato();
		contrato.setIdContrato(new ContratoPK(cpf, nomePlano));
		contrato.setDataContratacao(dataContratacao);
		return contrato;
	}

}
