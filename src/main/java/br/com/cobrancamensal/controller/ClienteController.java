package br.com.cobrancamensal.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cobrancamensal.controller.resource.ClienteResource;
import br.com.cobrancamensal.dto.NovoClienteDTO;
import br.com.cobrancamensal.service.ClienteService;

@RestController
@RequestMapping("clientes")
public class ClienteController implements ClienteResource {

	@Autowired
	private ClienteService clienteService;
	
	@Override
	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> criarCliente(@Valid  @RequestBody NovoClienteDTO novoClienteDTO) {
		clienteService.criarCliente(novoClienteDTO);
		return ResponseEntity.ok().build();
	}

}
