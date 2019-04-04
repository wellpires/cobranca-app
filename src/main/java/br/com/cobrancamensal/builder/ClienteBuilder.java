package br.com.cobrancamensal.builder;

import java.time.LocalDate;
import java.util.Date;

import br.com.cobrancamensal.enums.Sexo;
import br.com.cobrancamensal.model.Cliente;
import br.com.cobrancamensal.util.CobrancaUtil;

public class ClienteBuilder {

	private Long idCliente;
	private String nomeCliente;
	private Date dataNascimento;
	private Long cpf;
	private Sexo sexo;

	public ClienteBuilder idCliente(Long idCliente) {
		this.idCliente = idCliente;
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

	public ClienteBuilder cpf(String cpf) {
		this.cpf = Long.valueOf(cpf);
		return this;
	}

	public ClienteBuilder sexo(String sexo) {
		this.sexo = Sexo.parseOf(sexo);
		return this;
	}

	public Cliente build() {
		Cliente cliente = new Cliente();
		cliente.setIdCliente(idCliente);
		cliente.setNomeCliente(nomeCliente);
		cliente.setDataNascimento(dataNascimento);
		cliente.setCpf(cpf);
		cliente.setSexo(sexo);
		return cliente;
	}

}
