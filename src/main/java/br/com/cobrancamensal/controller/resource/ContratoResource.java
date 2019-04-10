package br.com.cobrancamensal.controller.resource;

import org.springframework.http.ResponseEntity;

import br.com.cobrancamensal.exception.ClienteNotFoundException;
import br.com.cobrancamensal.exception.ContratoDuplicadoException;
import br.com.cobrancamensal.exception.ContratoNotFoundException;
import br.com.cobrancamensal.exception.PlanoNotFoundException;
import br.com.cobrancamensal.response.ContratosResponse;
import br.com.cobrancamensal.response.DetalheContratoResponse;
import br.com.cobrancamensal.response.ErrorResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Serviço que gerencia o Contrato")
public interface ContratoResource {

	@ApiOperation(value = "Contratar um plano")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 409, message = "Conflict", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	ResponseEntity<Void> contratarPlano(String cpf, String nomePlano)
			throws ClienteNotFoundException, PlanoNotFoundException, ContratoDuplicadoException;

	@ApiOperation(value = "Listar planos")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ContratosResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	ResponseEntity<ContratosResponse> listarPlanos();

	@ApiOperation(value = "Buscar plano")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = DetalheContratoResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	ResponseEntity<DetalheContratoResponse> buscarPlano(String cpf, String nomePlano) throws ContratoNotFoundException;

}
