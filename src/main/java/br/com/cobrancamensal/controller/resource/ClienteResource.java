package br.com.cobrancamensal.controller.resource;

import org.springframework.http.ResponseEntity;

import br.com.cobrancamensal.dto.NovoClienteDTO;

public interface ClienteResource {

	ResponseEntity<Void> criarCliente(NovoClienteDTO novoClienteDTO);
	
}
