package br.com.cobrancamensal.service;

import java.util.List;

import br.com.cobrancamensal.dto.ClienteDTO;
import br.com.cobrancamensal.dto.NovoClienteDTO;

public interface ClienteService {

	void criarCliente(NovoClienteDTO novoClienteDTO);

	List<ClienteDTO> listarClientes();

}
