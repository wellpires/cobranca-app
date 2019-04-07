package br.com.cobrancamensal.builder;

import java.util.ArrayList;
import java.util.List;

import br.com.cobrancamensal.dto.ClienteDTO;
import br.com.cobrancamensal.util.CobrancaUtil;

public class ClienteDTOBuilder {

	private String nomeCliente;
	private String cpf;
	private Integer quantidadeItens = 0;

	public ClienteDTOBuilder nomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
		return this;
	}

	public ClienteDTOBuilder cpf(Long cpf) {
		this.cpf = String.valueOf(cpf);
		return this;
	}

	public ClienteDTOBuilder quantidadeItens(Integer quantidadeItens) {
		this.quantidadeItens = quantidadeItens;
		return this;
	}

	public ClienteDTO build() {
		ClienteDTO detalheClienteDTO = new ClienteDTO();
		detalheClienteDTO.setNome(nomeCliente);
		detalheClienteDTO.setCpf(cpf);
		return detalheClienteDTO;
	}

	public List<ClienteDTO> buildList() {
		List<ClienteDTO> clientes = new ArrayList<>();
		for (int i = 0; i < quantidadeItens; i++) {
			ClienteDTO clienteDTO = new ClienteDTO();
			clienteDTO.setNome(String.format("Nome %d", i));
			clienteDTO.setCpf(CobrancaUtil.generateLongValue().toString());
			clientes.add(clienteDTO);
		}
		return clientes;
	}

}
