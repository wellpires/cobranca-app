package br.com.cobrancamensal.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cobrancamensal.builder.ClienteBuilder;
import br.com.cobrancamensal.builder.DetalheClienteDTOBuilder;
import br.com.cobrancamensal.dto.AlterarClienteDTO;
import br.com.cobrancamensal.dto.ClienteDTO;
import br.com.cobrancamensal.dto.DetalheClienteDTO;
import br.com.cobrancamensal.dto.NovoClienteDTO;
import br.com.cobrancamensal.enums.EstadoCivil;
import br.com.cobrancamensal.exception.ClienteDuplicadoException;
import br.com.cobrancamensal.exception.ClienteNotFoundException;
import br.com.cobrancamensal.function.ClienteToDetalheClienteDTOFunction;
import br.com.cobrancamensal.model.Cliente;
import br.com.cobrancamensal.repository.ClienteRepository;
import br.com.cobrancamensal.service.ClienteService;
import br.com.cobrancamensal.util.CobrancaUtil;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public void criarCliente(NovoClienteDTO novoClienteDTO) throws ClienteDuplicadoException {

		if (clienteRepository.existsById(Long.valueOf(novoClienteDTO.getCpf()))) {
			throw new ClienteDuplicadoException();
		}

		Cliente novoCliente = new ClienteBuilder().nomeCliente(novoClienteDTO.getNome())
				.dataNascimento(novoClienteDTO.getDataNascimento()).cpf(novoClienteDTO.getCpf())
				.estadoCivil(novoClienteDTO.getEstadoCivil()).build();
		clienteRepository.save(novoCliente);

	}

	@Override
	public List<ClienteDTO> listarClientes() {
		List<Cliente> clientes = new ArrayList<>();
		CollectionUtils.addAll(clientes, clienteRepository.findAll().iterator());

		return clientes.stream().map(new ClienteToDetalheClienteDTOFunction()).collect(Collectors.toList());
	}

	@Override
	public DetalheClienteDTO buscarCliente(Long cpf) throws ClienteNotFoundException {
		Cliente cliente = buscarClientePorCPF(cpf);
		return new DetalheClienteDTOBuilder().nomeCliente(cliente.getNomeCliente()).cpf(cliente.getCpf())
				.dataNascimento(cliente.getDataNascimento()).estadoCivil(cliente.getEstadoCivil()).build();
	}

	@Override
	public void removerCliente(Long cpf) throws ClienteNotFoundException {
		Cliente cliente = buscarClientePorCPF(cpf);
		clienteRepository.delete(cliente);
	}

	@Override
	public void alterarCliente(Long cpf, AlterarClienteDTO alterarClienteDTO) throws ClienteNotFoundException {
		Cliente cliente = buscarClientePorCPF(cpf);
		cliente.setEstadoCivil(EstadoCivil.valueOf(alterarClienteDTO.getEstadoCivil()));
		cliente.setDataNascimento(CobrancaUtil.localDateToDate(alterarClienteDTO.getDataNascimento()));

		clienteRepository.save(cliente);

	}

	private Cliente buscarClientePorCPF(Long cpf) throws ClienteNotFoundException {
		return clienteRepository.findById(cpf).orElseThrow(ClienteNotFoundException::new);
	}

}
