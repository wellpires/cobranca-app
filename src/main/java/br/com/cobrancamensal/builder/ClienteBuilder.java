package br.com.cobrancamensal.builder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.cobrancamensal.enums.EstadoCivil;
import br.com.cobrancamensal.model.Cliente;
import br.com.cobrancamensal.util.CobrancaUtil;

public class ClienteBuilder {

	private Long cpf;
	private String nomeCliente;
	private Date dataNascimento;
	private EstadoCivil estadoCivil;
	private Integer quantidadeItens;

	public ClienteBuilder cpf(String cpf) {
		this.cpf = Long.valueOf(cpf);
		return this;
	}

	public ClienteBuilder nomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
		return this;
	}

	public ClienteBuilder dataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = CobrancaUtil.localDateToDate(dataNascimento);
		return this;
	}

	public ClienteBuilder estadoCivil(String estadoCivil) {
		this.estadoCivil = EstadoCivil.parseOf(estadoCivil);
		return this;
	}

	public ClienteBuilder quantidadeItens(Integer quantidadeItens) {
		this.quantidadeItens = quantidadeItens;
		return this;
	}

	public Cliente build() {
		Cliente cliente = new Cliente();
		cliente.setCpf(cpf);
		cliente.setNomeCliente(nomeCliente);
		cliente.setDataNascimento(dataNascimento);
		cliente.setEstadoCivil(estadoCivil);
		return cliente;
	}

	public List<Cliente> buildList() {
		List<Cliente> clientes = new ArrayList<>();
		for (int i = 0; i < quantidadeItens; i++) {
			clientes.add(new Cliente());
		}
		return clientes;
	}

}
