package br.com.cobrancamensal.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cobrancamensal.controller.resource.ClienteResource;
import br.com.cobrancamensal.dto.ClienteDTO;
import br.com.cobrancamensal.dto.ClientesDTO;
import br.com.cobrancamensal.dto.NovoClienteDTO;
import br.com.cobrancamensal.exception.ClienteAlreadyExistsException;
import br.com.cobrancamensal.service.ClienteService;

@RestController
@RequestMapping("api/v1/clientes")
public class ClienteController implements ClienteResource {

	@Autowired
	private ClienteService clienteService;

	@Override
	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> criarCliente(@Valid @RequestBody NovoClienteDTO novoClienteDTO) throws ClienteAlreadyExistsException {
		clienteService.criarCliente(novoClienteDTO);
		return ResponseEntity.noContent().build();
	}

	@Override
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ClientesDTO> listarClientes() {
		List<ClienteDTO> clientes = clienteService.listarClientes();
		return ResponseEntity.ok(new ClientesDTO(clientes));
	}

}
