package br.com.cobrancamensal.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.cobrancamensal.enums.EstadoCivil;

@Entity
@Table(name = "CLIENTES")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1864112342486393489L;

	@Id
	@Column(name = "CPF", nullable = false, unique = true)
	private Long cpf;

	@Column(name = "NOME_CLIENTE", nullable = false, length = 100, unique = true)
	private String nomeCliente;

	@Column(name = "DATA_NASCIMENTO")
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

	@Column(name = "ESTADO_CIVIL")
	@Enumerated(EnumType.STRING)
	private EstadoCivil estadoCivil;

	public Cliente() {
	}

	public Cliente(Long cpf) {
		this.cpf = cpf;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

}
