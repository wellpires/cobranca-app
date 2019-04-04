package br.com.cobrancamensal.model.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.cobrancamensal.model.Cliente;
import br.com.cobrancamensal.model.Plano;

@Embeddable
public class ContratoPK implements Serializable {

	private static final long serialVersionUID = -7724317796561762562L;

	@ManyToOne
	@JoinColumn(name = "ID_CLIENTE")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "ID_PLANO")
	private Plano plano;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Plano getPlano() {
		return plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}

}
