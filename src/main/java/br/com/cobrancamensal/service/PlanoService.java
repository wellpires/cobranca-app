package br.com.cobrancamensal.service;

import java.util.List;

import br.com.cobrancamensal.dto.NovoPlanoDTO;
import br.com.cobrancamensal.dto.PlanoDTO;

public interface PlanoService {

	void criarPlano(NovoPlanoDTO novoPlanoDTO);

	List<PlanoDTO> listarPlanos();

}
