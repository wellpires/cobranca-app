package br.com.cobrancamensal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cobrancamensal.controller.resource.ContratoResource;
import br.com.cobrancamensal.exception.ClienteNotFoundException;
import br.com.cobrancamensal.exception.ContratoDuplicadoException;
import br.com.cobrancamensal.exception.PlanoNotFoundException;
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

}
