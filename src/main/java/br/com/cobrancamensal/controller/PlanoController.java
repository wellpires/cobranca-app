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
import br.com.cobrancamensal.dto.DetalhePlanoDTO;
import br.com.cobrancamensal.dto.PlanoDTO;
import br.com.cobrancamensal.exception.PlanoDuplicadoException;
import br.com.cobrancamensal.exception.PlanoNotFoundException;
import br.com.cobrancamensal.request.AlterarPlanoRequest;
import br.com.cobrancamensal.request.NovoPlanoRequest;
import br.com.cobrancamensal.response.DetalhePlanoResponse;
import br.com.cobrancamensal.response.PlanosResponse;
import br.com.cobrancamensal.service.PlanoService;

@RestController
@RequestMapping("api/v1/planos")
public class PlanoController implements PlanoResource {

	@Autowired
	private PlanoService planoService;

	@Override
	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> criarPlano(@Valid @RequestBody NovoPlanoRequest novoPlanoRequest)
			throws PlanoDuplicadoException {
		planoService.criarPlano(novoPlanoRequest.getNovoPlanoDTO());
		return ResponseEntity.noContent().build();
	}

	@Override
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PlanosResponse> listarPlanos() {
		List<PlanoDTO> planos = planoService.listarPlanos();
		return ResponseEntity.ok(new PlanosResponse(planos));
	}

	@Override
	public ResponseEntity<DetalhePlanoResponse> buscarPlano(String nomePlano) throws PlanoNotFoundException {
		DetalhePlanoDTO detalhePlanoDTO = planoService.buscarPlano(nomePlano);
		return ResponseEntity.ok(new DetalhePlanoResponse(detalhePlanoDTO));
	}

	@Override
	public ResponseEntity<Void> removerPlano(String nomePlano) throws PlanoNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> alterarPlano(AlterarPlanoRequest alterarPlanoRequest, String nomePlano)
			throws PlanoNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
