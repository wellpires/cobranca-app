package br.com.cobrancamensal.controller.resource;

import org.springframework.http.ResponseEntity;

import br.com.cobrancamensal.exception.PlanoDuplicadoException;
import br.com.cobrancamensal.exception.PlanoNotFoundException;
import br.com.cobrancamensal.request.AlterarPlanoRequest;
import br.com.cobrancamensal.request.NovoPlanoRequest;
import br.com.cobrancamensal.response.DetalhePlanoResponse;
import br.com.cobrancamensal.response.ErrorResponse;
import br.com.cobrancamensal.response.PlanosResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Serviço que gerencia o Plano")
public interface PlanoResource {

	@ApiOperation(value = "Criar um novo plano")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 409, message = "Conflict", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	ResponseEntity<Void> criarPlano(NovoPlanoRequest novoPlanoRequest) throws PlanoDuplicadoException;

	@ApiOperation(value = "Listar planos")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = PlanosResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	ResponseEntity<PlanosResponse> listarPlanos();

	@ApiOperation(value = "Buscar plano")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = DetalhePlanoResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	ResponseEntity<DetalhePlanoResponse> buscarPlano(String nomePlano) throws PlanoNotFoundException;

	@ApiOperation(value = "Remove plano")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	ResponseEntity<Void> removerPlano(String nomePlano) throws PlanoNotFoundException;

	@ApiOperation(value = "Alterar plano")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	ResponseEntity<Void> alterarPlano(AlterarPlanoRequest alterarPlanoRequest, String nomePlano)
			throws PlanoNotFoundException;

}
