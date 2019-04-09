package br.com.cobrancamensal.response;

import java.util.List;

import br.com.cobrancamensal.dto.ClienteDTO;

public class ClientesResponse {

	private List<ClienteDTO> clientes;

	public ClientesResponse() {
	}

	public ClientesResponse(List<ClienteDTO> clientes) {
		this.clientes = clientes;
	}

	public List<ClienteDTO> getClientes() {
		return clientes;
	}

	public void setClientes(List<ClienteDTO> clientes) {
		this.clientes = clientes;
	}

}
