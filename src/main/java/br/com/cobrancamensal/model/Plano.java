package br.com.cobrancamensal.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PLANOS")
public class Plano implements Serializable {

	private static final long serialVersionUID = 9154646545393470236L;

	@Id
	@Column(name = "NOME_PLANO", length = 40, nullable = false)
	private String nomePlano;

	@Column(name = "VALOR", nullable = false)
	private Double valor;

	public Plano() {
	}

	public Plano(String nomePlano) {
		this.nomePlano = nomePlano;
	}

	public String getNomePlano() {
		return nomePlano;
	}

	public void setNomePlano(String nomePlano) {
		this.nomePlano = nomePlano;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
