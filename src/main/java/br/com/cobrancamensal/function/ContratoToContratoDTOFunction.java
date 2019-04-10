package br.com.cobrancamensal.function;

import java.util.function.Function;

import br.com.cobrancamensal.builder.ContratoDTOBuilder;
import br.com.cobrancamensal.dto.ContratoDTO;
import br.com.cobrancamensal.model.Contrato;

public class ContratoToContratoDTOFunction implements Function<Contrato, ContratoDTO> {

	@Override
	public ContratoDTO apply(Contrato contrato) {
		return new ContratoDTOBuilder().cpf(contrato.getIdContrato().getCliente().getCpf())
				.nomePlano(contrato.getIdContrato().getPlano().getNomePlano()).build();
	}

}
