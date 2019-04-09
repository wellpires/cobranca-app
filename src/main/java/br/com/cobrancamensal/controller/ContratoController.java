package br.com.cobrancamensal.controller;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cobrancamensal.controller.resource.ContratoResource;
import br.com.cobrancamensal.exception.ClienteNotFoundException;
import br.com.cobrancamensal.exception.ContratoDuplicadoException;
import br.com.cobrancamensal.exception.PlanoNotFoundException;
import br.com.cobrancamensal.service.ContratoService;
import br.com.cobrancamensal.util.Constantes;

@RestController
@RequestMapping("api/v1/contrato")
@Validated
public class ContratoController implements ContratoResource {

	@Autowired
	private ContratoService contratoService;

	@Override
	@PostMapping(path = "/cliente/{cpf}/plano/{nomePlano}/contratar")
	public ResponseEntity<Void> contratarPlano(
			@PathVariable("cpf") @NotBlank(message = Constantes.CAMPO_CPF_OBRIGATORIO) String cpf,
			@PathVariable("nomePlano") @NotBlank(message = Constantes.CAMPO_NOME_PLANO_OBRIGATORIO) @Size(max = 40, message = Constantes.CAMPO_NOME_PLANO_TAMANHO_MAXIMO) String nomePlano)
			throws ClienteNotFoundException, PlanoNotFoundException, ContratoDuplicadoException {

		contratoService.contratarPlano(Long.valueOf(cpf), nomePlano);
		return ResponseEntity.noContent().build();
	}

}
