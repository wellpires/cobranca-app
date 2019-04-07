package br.com.cobrancamensal.function;

import java.util.function.Function;

import br.com.cobrancamensal.builder.PlanoDTOBuilder;
import br.com.cobrancamensal.dto.PlanoDTO;
import br.com.cobrancamensal.model.Plano;

public class PlanoToPlanoDTOFunction implements Function<Plano, PlanoDTO> {

	@Override
	public PlanoDTO apply(Plano plano) {
		return new PlanoDTOBuilder().nome(plano.getNomePlano()).build();
	}

}
