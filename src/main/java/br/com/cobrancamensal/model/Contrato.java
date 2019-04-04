package br.com.cobrancamensal.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.cobrancamensal.model.pk.ContratoPK;

@Entity
@Table(name = "CONTRATOS")
public class Contrato implements Serializable {

	private static final long serialVersionUID = 458128316375367077L;

	@EmbeddedId
	private ContratoPK idContrato;

	@Column(name = "DT_CONTRATACAO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataContratacao;

	public ContratoPK getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(ContratoPK idContrato) {
		this.idContrato = idContrato;
	}

	public Date getDataContratacao() {
		return dataContratacao;
	}

	public void setDataContratacao(Date dataContratacao) {
		this.dataContratacao = dataContratacao;
	}

}
