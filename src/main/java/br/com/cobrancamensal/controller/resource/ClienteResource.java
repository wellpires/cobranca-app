package br.com.cobrancamensal.controller.resource;

import org.springframework.http.ResponseEntity;

import br.com.cobrancamensal.exception.ClienteDuplicadoException;
import br.com.cobrancamensal.exception.ClienteNotFoundException;
import br.com.cobrancamensal.request.AlterarClienteRequest;
import br.com.cobrancamensal.request.NovoClienteRequest;
import br.com.cobrancamensal.response.ClientesResponse;
import br.com.cobrancamensal.response.DetalheClienteResponse;
import br.com.cobrancamensal.response.ErrorResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Serviço que gerencia o Cliente")
public interface ClienteResource {

	@ApiOperation(value = "Criar um novo cliente")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 409, message = "Conflict", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	ResponseEntity<Void> criarCliente(NovoClienteRequest novoClienteRequest) throws ClienteDuplicadoException;

	@ApiOperation(value = "Listar clientes")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ClientesResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	ResponseEntity<ClientesResponse> listarClientes();

	@ApiOperation(value = "Buscar detalhe do cliente")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = DetalheClienteResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	ResponseEntity<DetalheClienteResponse> buscarCliente(Long cpf) throws ClienteNotFoundException;

	@ApiOperation(value = "Remove cliente")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	ResponseEntity<Void> removerCliente(Long cpf) throws ClienteNotFoundException;

	@ApiOperation(value = "Alterar cliente")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	ResponseEntity<Void> alterarCliente(AlterarClienteRequest alterarClienteRequest, Long cpf)
			throws ClienteNotFoundException;

}
