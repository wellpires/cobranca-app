package br.com.cobrancamensal.controller.resource;

import org.springframework.http.ResponseEntity;

import br.com.cobrancamensal.exception.ClienteNotFoundException;
import br.com.cobrancamensal.exception.ContratoAlreadyExistsException;
import br.com.cobrancamensal.exception.PlanoNotFoundException;

public interface ContratoResource {

	ResponseEntity<Void> contratarPlano(Long cpf, String nomePlano)
			throws ClienteNotFoundException, PlanoNotFoundException, ContratoAlreadyExistsException;

}
