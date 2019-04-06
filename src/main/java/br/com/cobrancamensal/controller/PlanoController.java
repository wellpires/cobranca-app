package br.com.cobrancamensal.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cobrancamensal.controller.resource.PlanoResource;
import br.com.cobrancamensal.dto.NovoPlanoDTO;
import br.com.cobrancamensal.dto.PlanoDTO;
import br.com.cobrancamensal.dto.PlanosDTO;
import br.com.cobrancamensal.service.PlanoService;

@RestController
@RequestMapping("api/v1/planos")
public class PlanoController implements PlanoResource {

	@Autowired
	private PlanoService planoService;

	@Override
	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> criarPlano(@Valid @RequestBody NovoPlanoDTO novoPlanoDTO) {
		planoService.criarPlano(novoPlanoDTO);
		return ResponseEntity.noContent().build();
	}

	@Override
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PlanosDTO> listarPlanos() {
		List<PlanoDTO> planos = planoService.listarPlanos();
		return ResponseEntity.ok(new PlanosDTO(planos));
	}

}
