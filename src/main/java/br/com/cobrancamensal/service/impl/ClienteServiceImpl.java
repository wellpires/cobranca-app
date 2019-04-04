package br.com.cobrancamensal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cobrancamensal.builder.ClienteBuilder;
import br.com.cobrancamensal.dto.NovoClienteDTO;
import br.com.cobrancamensal.model.Cliente;
import br.com.cobrancamensal.repository.ClienteRepository;
import br.com.cobrancamensal.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public void criarCliente(NovoClienteDTO novoClienteDTO) {
		Cliente novoCliente = new ClienteBuilder().nomeCliente(novoClienteDTO.getNomeCliente())
				.dataNascimento(novoClienteDTO.getDataNascimento()).cpf(novoClienteDTO.getCpf())
				.sexo(novoClienteDTO.getSexo()).build();
		clienteRepository.save(novoCliente);

	}

}
