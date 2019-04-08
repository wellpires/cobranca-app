package br.com.cobrancamensal.controller.resource;

import org.springframework.http.ResponseEntity;

import br.com.cobrancamensal.exception.ClienteNotFoundException;
import br.com.cobrancamensal.exception.ContratoAlreadyExistsException;
import br.com.cobrancamensal.exception.PlanoNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Serviço que gerencia o Contrato")
public interface ContratoResource {

	@ApiOperation(value = "Contratar um plano")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	ResponseEntity<Void> contratarPlano(String cpf, String nomePlano)
			throws ClienteNotFoundException, PlanoNotFoundException, ContratoAlreadyExistsException;

}
