package br.com.cobrancamensal.service;

import java.util.List;

import br.com.cobrancamensal.dto.ContratoDTO;
import br.com.cobrancamensal.dto.DetalheContratoDTO;
import br.com.cobrancamensal.exception.ClienteNotFoundException;
import br.com.cobrancamensal.exception.ContratoDuplicadoException;
import br.com.cobrancamensal.exception.ContratoNotFoundException;
import br.com.cobrancamensal.exception.PlanoNotFoundException;

public interface ContratoService {

	void contratarPlano(Long cpf, String nomePlano)
			throws ClienteNotFoundException, PlanoNotFoundException, ContratoDuplicadoException;

	List<ContratoDTO> listarContratos();

	DetalheContratoDTO buscarContrato(String cpf, String nomePlano) throws ContratoNotFoundException;

}
