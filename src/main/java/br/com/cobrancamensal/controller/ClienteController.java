package br.com.cobrancamensal.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cobrancamensal.controller.resource.ClienteResource;
import br.com.cobrancamensal.dto.ClienteDTO;
import br.com.cobrancamensal.dto.DetalheClienteDTO;
import br.com.cobrancamensal.exception.ClienteDuplicadoException;
import br.com.cobrancamensal.exception.ClienteNotFoundException;
import br.com.cobrancamensal.request.AlterarClienteRequest;
import br.com.cobrancamensal.request.NovoClienteRequest;
import br.com.cobrancamensal.response.ClientesResponse;
import br.com.cobrancamensal.response.DetalheClienteResponse;
import br.com.cobrancamensal.service.ClienteService;

@RestController
@RequestMapping("api/v1/clientes")
public class ClienteController implements ClienteResource {

	@Autowired
	private ClienteService clienteService;

	@Override
	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> criarCliente(@Valid @RequestBody NovoClienteRequest novoClienteRequest)
			throws ClienteDuplicadoException {
		clienteService.criarCliente(novoClienteRequest.getNovoClienteDTO());
		return ResponseEntity.noContent().build();
	}

	@Override
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ClientesResponse> listarClientes() {
		List<ClienteDTO> clientes = clienteService.listarClientes();
		return ResponseEntity.ok(new ClientesResponse(clientes));
	}

	@Override
	@GetMapping(path = "/{cpf}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<DetalheClienteResponse> buscarCliente(@PathVariable("cpf") Long cpf)
			throws ClienteNotFoundException {
		DetalheClienteDTO detalheClienteDTO = clienteService.buscarCliente(cpf);
		return ResponseEntity.ok(new DetalheClienteResponse(detalheClienteDTO));
	}

	@Override
	@DeleteMapping(path = "/{cpf}")
	public ResponseEntity<Void> removerCliente(@PathVariable("cpf") Long cpf) throws ClienteNotFoundException {
		clienteService.removerCliente(cpf);
		return ResponseEntity.noContent().build();
	}

	@Override
	@PatchMapping(path = "/{cpf}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> alterarCliente(@Valid @RequestBody AlterarClienteRequest alterarClienteRequest,
			@PathVariable("cpf") Long cpf) throws ClienteNotFoundException {
		clienteService.alterarCliente(cpf, alterarClienteRequest.getDetalheClienteDTO());
		return ResponseEntity.noContent().build();
	}

}
