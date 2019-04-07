package br.com.cobrancamensal.service;

import java.util.List;

import br.com.cobrancamensal.dto.ClienteDTO;
import br.com.cobrancamensal.dto.NovoClienteDTO;
import br.com.cobrancamensal.exception.ClienteAlreadyExistsException;
import br.com.cobrancamensal.exception.ClienteNotFoundException;

public interface ClienteService {

	void criarCliente(NovoClienteDTO novoClienteDTO) throws ClienteAlreadyExistsException;

	List<ClienteDTO> listarClientes();

	ClienteDTO buscarCliente(Long idCliente) throws ClienteNotFoundException;

}
