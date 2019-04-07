package br.com.cobrancamensal.controller.resource;

import org.springframework.http.ResponseEntity;

import br.com.cobrancamensal.dto.ClientesDTO;
import br.com.cobrancamensal.dto.NovoClienteDTO;
import br.com.cobrancamensal.exception.ClienteAlreadyExistsException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Serviço que gerencia o Cliente")
public interface ClienteResource {

	@ApiOperation(value = "Criar um novo cliente")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	ResponseEntity<Void> criarCliente(NovoClienteDTO novoClienteDTO) throws ClienteAlreadyExistsException;

	@ApiOperation(value = "Listar clientes")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ClientesDTO.class),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	ResponseEntity<ClientesDTO> listarClientes();

}
