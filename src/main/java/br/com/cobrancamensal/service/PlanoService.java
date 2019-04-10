package br.com.cobrancamensal.service;

import java.util.List;

import br.com.cobrancamensal.dto.DetalhePlanoDTO;
import br.com.cobrancamensal.dto.NovoPlanoDTO;
import br.com.cobrancamensal.dto.PlanoDTO;
import br.com.cobrancamensal.exception.PlanoDuplicadoException;
import br.com.cobrancamensal.exception.PlanoNotFoundException;

public interface PlanoService {

	void criarPlano(NovoPlanoDTO novoPlanoDTO) throws PlanoDuplicadoException;

	List<PlanoDTO> listarPlanos();

	DetalhePlanoDTO buscarPlano(String nomePlano) throws PlanoNotFoundException;

}
