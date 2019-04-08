package br.com.cobrancamensal.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cobrancamensal.dto.ClienteDTO;
import br.com.cobrancamensal.dto.PlanoDTO;
import br.com.cobrancamensal.exception.ClienteNotFoundException;
import br.com.cobrancamensal.exception.ContratoAlreadyExistsException;
import br.com.cobrancamensal.exception.PlanoNotFoundException;
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
			throws ClienteNotFoundException, PlanoNotFoundException, ContratoAlreadyExistsException {
		ClienteDTO clienteDTO = clienteService.buscarCliente(cpf);
		PlanoDTO planoDTO = planoService.buscarPlano(nomePlano);
		ContratoPK idContrato = new ContratoPK(clienteDTO.getCpf(), planoDTO.getNome());

		if (contratoRepository.existsById(idContrato)) {
			throw new ContratoAlreadyExistsException(clienteDTO.getCpf(), planoDTO.getNome());
		}

		Contrato contrato = new Contrato();
		contrato.setIdContrato(idContrato);
		contrato.setDataContratacao(new Date());

		contratoRepository.save(contrato);
	}

}
