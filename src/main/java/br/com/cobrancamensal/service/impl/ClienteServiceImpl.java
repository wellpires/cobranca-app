package br.com.cobrancamensal.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cobrancamensal.builder.ClienteBuilder;
import br.com.cobrancamensal.dto.ClienteDTO;
import br.com.cobrancamensal.dto.NovoClienteDTO;
import br.com.cobrancamensal.function.ClienteToDetalheClienteDTOFunction;
import br.com.cobrancamensal.model.Cliente;
import br.com.cobrancamensal.repository.ClienteRepository;
import br.com.cobrancamensal.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public void criarCliente(NovoClienteDTO novoClienteDTO) {
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

}
