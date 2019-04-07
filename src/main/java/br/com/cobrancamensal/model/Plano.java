package br.com.cobrancamensal.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PLANOS")
public class Plano implements Serializable {

	private static final long serialVersionUID = 9154646545393470236L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PLANO")
	private Long idPlano;

	@Column(name = "NOME_PLANO", length = 40)
	private String nomePlano;

	@Column(name = "VALOR")
	private Double valor;

	public Long getIdPlano() {
		return idPlano;
	}

	public void setIdPlano(Long idPlano) {
		this.idPlano = idPlano;
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
