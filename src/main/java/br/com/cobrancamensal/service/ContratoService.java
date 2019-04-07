package br.com.cobrancamensal.service;

import br.com.cobrancamensal.exception.ClienteNotFoundException;
import br.com.cobrancamensal.exception.ContratoAlreadyExistsException;
import br.com.cobrancamensal.exception.PlanoNotFoundException;

public interface ContratoService {

	void contratarPlano(Long cpf, String nomePlano)
			throws ClienteNotFoundException, PlanoNotFoundException, ContratoAlreadyExistsException;

}
