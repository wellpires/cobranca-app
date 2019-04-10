package br.com.cobrancamensal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cobrancamensal.controller.resource.ContratoResource;
import br.com.cobrancamensal.dto.ContratoDTO;
import br.com.cobrancamensal.dto.DetalheContratoDTO;
import br.com.cobrancamensal.exception.ClienteNotFoundException;
import br.com.cobrancamensal.exception.ContratoDuplicadoException;
import br.com.cobrancamensal.exception.ContratoNotFoundException;
import br.com.cobrancamensal.exception.PlanoNotFoundException;
import br.com.cobrancamensal.response.ContratosResponse;
import br.com.cobrancamensal.response.DetalheContratoResponse;
import br.com.cobrancamensal.service.ContratoService;

@RestController
@RequestMapping("api/v1/contratos")
public class ContratoController implements ContratoResource {

	@Autowired
	private ContratoService contratoService;

	@Override
	@PostMapping(path = "/cliente/{cpf}/plano/{nomePlano}/contratar")
	public ResponseEntity<Void> contratarPlano(@PathVariable("cpf") String cpf,
			@PathVariable("nomePlano") String nomePlano)
			throws ClienteNotFoundException, PlanoNotFoundException, ContratoDuplicadoException {

		contratoService.contratarPlano(Long.valueOf(cpf), nomePlano);
		return ResponseEntity.noContent().build();
	}

	@Override
	@GetMapping(produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
	public ResponseEntity<ContratosResponse> listarPlanos() {
		List<ContratoDTO> contratoDTOs = contratoService.listarContratos();
		return ResponseEntity.ok(new ContratosResponse(contratoDTOs));
	}

	@Override
	@GetMapping(path = "/cliente/{cpf}/plano/{nomePlano}")
	public ResponseEntity<DetalheContratoResponse> buscarPlano(@PathVariable("cpf") String cpf,
			@PathVariable("nomePlano") String nomePlano) throws ContratoNotFoundException {
		
		DetalheContratoDTO detalheContratoDTO = contratoService.buscarContrato(cpf, nomePlano);
		return ResponseEntity.ok(new DetalheContratoResponse(detalheContratoDTO));
	}

}
