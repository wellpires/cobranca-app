package br.com.cobrancamensal.controller.resource;

import org.springframework.http.ResponseEntity;

import br.com.cobrancamensal.dto.NovoPlanoDTO;
import br.com.cobrancamensal.dto.PlanosDTO;
import br.com.cobrancamensal.exception.PlanoDuplicadoException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Serviço que gerencia o Plano")
public interface PlanoResource {

	@ApiOperation(value = "Criar um novo plano")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	ResponseEntity<Void> criarPlano(NovoPlanoDTO novoPlanoDTO) throws PlanoDuplicadoException;

	@ApiOperation(value = "Listar planos")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = PlanosDTO.class),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	ResponseEntity<PlanosDTO> listarPlanos();

}
