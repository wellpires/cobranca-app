package br.com.cobrancamensal.request;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.cobrancamensal.dto.NovoPlanoDTO;
import br.com.cobrancamensal.util.Constantes;

public class NovoPlanoRequest {

	@JsonProperty("plano")
	@NotNull(message = Constantes.PLANO_OBRIGATORIO)
	@Valid
	private NovoPlanoDTO novoPlanoDTO;

	public NovoPlanoDTO getNovoPlanoDTO() {
		return novoPlanoDTO;
	}

	public void setNovoPlanoDTO(NovoPlanoDTO novoPlanoDTO) {
		this.novoPlanoDTO = novoPlanoDTO;
	}

}
