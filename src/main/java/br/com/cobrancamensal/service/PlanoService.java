package br.com.cobrancamensal.service;

import java.util.List;

import br.com.cobrancamensal.dto.NovoPlanoDTO;
import br.com.cobrancamensal.dto.PlanoDTO;
import br.com.cobrancamensal.exception.PlanoAlreadyExistsException;
import br.com.cobrancamensal.exception.PlanoNotFoundException;

public interface PlanoService {

	void criarPlano(NovoPlanoDTO novoPlanoDTO) throws PlanoAlreadyExistsException;

	List<PlanoDTO> listarPlanos();

	PlanoDTO buscarPlano(String nomePlano) throws PlanoNotFoundException;

}