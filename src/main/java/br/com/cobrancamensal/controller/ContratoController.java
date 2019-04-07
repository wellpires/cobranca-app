package br.com.cobrancamensal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cobrancamensal.controller.resource.ContratoResource;
import br.com.cobrancamensal.exception.ClienteNotFoundException;
import br.com.cobrancamensal.exception.ContratoAlreadyExistsException;
import br.com.cobrancamensal.exception.PlanoNotFoundException;
import br.com.cobrancamensal.service.ContratoService;

@RestController
@RequestMapping("api/v1/contrato")
public class ContratoController implements ContratoResource {

	@Autowired
	private ContratoService contratoService;

	@Override
	@PutMapping(path = "/cliente/{cpf}/plano/{nomePlano}/contratar")
	public ResponseEntity<Void> contratarPlano(@PathVariable("cpf") Long cpf,
			@PathVariable("nomePlano") String nomePlano) throws ClienteNotFoundException, PlanoNotFoundException, ContratoAlreadyExistsException {
		contratoService.contratarPlano(cpf, nomePlano);
		return ResponseEntity.noContent().build();
	}

}
