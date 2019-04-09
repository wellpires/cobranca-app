package br.com.cobrancamensal.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cobrancamensal.builder.PlanoBuilder;
import br.com.cobrancamensal.builder.PlanoDTOBuilder;
import br.com.cobrancamensal.dto.NovoPlanoDTO;
import br.com.cobrancamensal.dto.PlanoDTO;
import br.com.cobrancamensal.exception.PlanoDuplicadoException;
import br.com.cobrancamensal.exception.PlanoNotFoundException;
import br.com.cobrancamensal.function.PlanoToPlanoDTOFunction;
import br.com.cobrancamensal.model.Plano;
import br.com.cobrancamensal.repository.PlanoRepository;
import br.com.cobrancamensal.service.PlanoService;

@Service
public class PlanoServiceImpl implements PlanoService {

	@Autowired
	private PlanoRepository planoRepository;

	@Override
	public void criarPlano(NovoPlanoDTO novoPlanoDTO) throws PlanoDuplicadoException {

		if (planoRepository.existsById(novoPlanoDTO.getNome())) {
			throw new PlanoDuplicadoException();
		}

		Plano plano = new PlanoBuilder().nomePlano(novoPlanoDTO.getNome()).valor(novoPlanoDTO.getValor()).build();
		planoRepository.save(plano);
	}

	@Override
	public List<PlanoDTO> listarPlanos() {
		List<Plano> planos = new ArrayList<>();
		CollectionUtils.addAll(planos, planoRepository.findAll().iterator());
		return planos.stream().map(new PlanoToPlanoDTOFunction()).collect(Collectors.toList());
	}

	@Override
	public PlanoDTO buscarPlano(String nomePlano) throws PlanoNotFoundException {
		Plano plano = planoRepository.findByNomePlano(nomePlano).orElseThrow(PlanoNotFoundException::new);
		return new PlanoDTOBuilder().nome(plano.getNomePlano()).build();
	}

}
