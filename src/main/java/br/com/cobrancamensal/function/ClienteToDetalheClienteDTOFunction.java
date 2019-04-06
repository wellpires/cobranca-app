package br.com.cobrancamensal.function;

import java.util.function.Function;

import br.com.cobrancamensal.builder.ClienteDTOBuilder;
import br.com.cobrancamensal.dto.ClienteDTO;
import br.com.cobrancamensal.model.Cliente;

public class ClienteToDetalheClienteDTOFunction implements Function<Cliente, ClienteDTO> {

	@Override
	public ClienteDTO apply(Cliente cliente) {
		return new ClienteDTOBuilder().id(cliente.getIdCliente()).nomeCliente(cliente.getNomeCliente())
				.cpf(cliente.getCpf()).build();
	}

}
