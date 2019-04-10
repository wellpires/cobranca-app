package br.com.cobrancamensal.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cobrancamensal.builder.DetalheContratoDTOBuilder;
import br.com.cobrancamensal.dto.ContratoDTO;
import br.com.cobrancamensal.dto.DetalheClienteDTO;
import br.com.cobrancamensal.dto.DetalheContratoDTO;
import br.com.cobrancamensal.dto.DetalhePlanoDTO;
import br.com.cobrancamensal.exception.ClienteNotFoundException;
import br.com.cobrancamensal.exception.ContratoDuplicadoException;
import br.com.cobrancamensal.exception.ContratoNotFoundException;
import br.com.cobrancamensal.exception.PlanoNotFoundException;
import br.com.cobrancamensal.function.ContratoToContratoDTOFunction;
import br.com.cobrancamensal.model.Contrato;
import br.com.cobrancamensal.model.pk.ContratoPK;
import br.com.cobrancamensal.repository.ContratoRepository;
import br.com.cobrancamensal.service.ClienteService;
import br.com.cobrancamensal.service.ContratoService;
import br.com.cobrancamensal.service.PlanoService;

@Service
public class ContratoServiceImpl implements ContratoService {

	@Autowired
	private ContratoRepository contratoRepository;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private PlanoService planoService;

	@Override
	public void contratarPlano(Long cpf, String nomePlano)
			throws ClienteNotFoundException, PlanoNotFoundException, ContratoDuplicadoException {
		DetalheClienteDTO detalheClienteDTO = clienteService.buscarCliente(cpf);
		DetalhePlanoDTO detalhePlanoDTO = planoService.buscarPlano(nomePlano);
		ContratoPK idContrato = new ContratoPK(detalheClienteDTO.getCpf(), detalhePlanoDTO.getNome());

		if (contratoRepository.existsById(idContrato)) {
			throw new ContratoDuplicadoException(detalheClienteDTO.getCpf(), detalhePlanoDTO.getNome());
		}

		Contrato contrato = new Contrato();
		contrato.setIdContrato(idContrato);
		contrato.setDataContratacao(new Date());

		contratoRepository.save(contrato);
	}

	@Override
	public List<ContratoDTO> listarContratos() {

		List<Contrato> contratos = new ArrayList<>();
		CollectionUtils.addAll(contratos, contratoRepository.findAll().iterator());

		return contratos.stream().map(new ContratoToContratoDTOFunction()).collect(Collectors.toList());
	}

	@Override
	public DetalheContratoDTO buscarContrato(String cpf, String nomePlano) throws ContratoNotFoundException {
		Contrato contrato = contratoRepository.findById(new ContratoPK(cpf, nomePlano))
				.orElseThrow(ContratoNotFoundException::new);
		return new DetalheContratoDTOBuilder().cpf(contrato.getIdContrato().getCliente().getCpf())
				.nomePlano(contrato.getIdContrato().getPlano().getNomePlano())
				.dataContratacao(contrato.getDataContratacao()).build();
	}

}
