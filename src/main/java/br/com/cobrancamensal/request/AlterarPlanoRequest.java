package br.com.cobrancamensal.request;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.cobrancamensal.dto.AlterarPlanoDTO;
import br.com.cobrancamensal.util.Constantes;

public class AlterarPlanoRequest {

	@JsonProperty("plano")
	@NotNull(message = Constantes.PLANO_OBRIGATORIO)
	@Valid
	private AlterarPlanoDTO alterarPlanoDTO;

	public AlterarPlanoRequest(AlterarPlanoDTO alterarPlanoDTO) {
		this.alterarPlanoDTO = alterarPlanoDTO;
	}

	public AlterarPlanoRequest() {
	}

	public AlterarPlanoDTO getAlterarPlanoDTO() {
		return alterarPlanoDTO;
	}

	public void setAlterarPlanoDTO(AlterarPlanoDTO alterarPlanoDTO) {
		this.alterarPlanoDTO = alterarPlanoDTO;
	}

}
