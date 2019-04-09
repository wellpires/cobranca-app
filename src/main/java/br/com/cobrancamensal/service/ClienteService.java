package br.com.cobrancamensal.service;

import java.util.List;

import br.com.cobrancamensal.dto.AlterarClienteDTO;
import br.com.cobrancamensal.dto.ClienteDTO;
import br.com.cobrancamensal.dto.DetalheClienteDTO;
import br.com.cobrancamensal.dto.NovoClienteDTO;
import br.com.cobrancamensal.exception.ClienteDuplicadoException;
import br.com.cobrancamensal.exception.ClienteNotFoundException;

public interface ClienteService {

	void criarCliente(NovoClienteDTO novoClienteDTO) throws ClienteDuplicadoException;

	List<ClienteDTO> listarClientes();

	DetalheClienteDTO buscarCliente(Long idCliente) throws ClienteNotFoundException;

	void removerCliente(Long cpf) throws ClienteNotFoundException;

	void alterarCliente(Long cpf, AlterarClienteDTO alterarClienteDTO) throws ClienteNotFoundException;

}
