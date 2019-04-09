package br.com.cobrancamensal.service;

import br.com.cobrancamensal.exception.ClienteNotFoundException;
import br.com.cobrancamensal.exception.ContratoDuplicadoException;
import br.com.cobrancamensal.exception.PlanoNotFoundException;

public interface ContratoService {

	void contratarPlano(Long cpf, String nomePlano)
			throws ClienteNotFoundException, PlanoNotFoundException, ContratoDuplicadoException;

}
