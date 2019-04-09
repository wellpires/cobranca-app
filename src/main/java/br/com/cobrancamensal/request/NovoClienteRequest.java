package br.com.cobrancamensal.request;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.cobrancamensal.dto.NovoClienteDTO;
import br.com.cobrancamensal.util.Constantes;

public class NovoClienteRequest {

	@JsonProperty("cliente")
	@NotNull(message = Constantes.CLIENTE_OBRIGATORIO)
	@Valid
	private NovoClienteDTO novoClienteDTO;

	public NovoClienteRequest(NovoClienteDTO novoClienteDTO) {
		this.novoClienteDTO = novoClienteDTO;
	}

	public NovoClienteRequest() {
	}

	public NovoClienteDTO getNovoClienteDTO() {
		return novoClienteDTO;
	}

	public void setNovoClienteDTO(NovoClienteDTO novoClienteDTO) {
		this.novoClienteDTO = novoClienteDTO;
	}

}
