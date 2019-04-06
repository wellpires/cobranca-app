package br.com.cobrancamensal.dto;

import java.util.List;

public class ClientesDTO {

	private List<ClienteDTO> clientes;

	public ClientesDTO() {
	}

	public ClientesDTO(List<ClienteDTO> clientes) {
		this.clientes = clientes;
	}

	public List<ClienteDTO> getClientes() {
		return clientes;
	}

	public void setClientes(List<ClienteDTO> clientes) {
		this.clientes = clientes;
	}

}
