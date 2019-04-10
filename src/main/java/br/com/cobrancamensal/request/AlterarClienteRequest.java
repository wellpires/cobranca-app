package br.com.cobrancamensal.request;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.cobrancamensal.dto.AlterarClienteDTO;
import br.com.cobrancamensal.util.Constantes;

public class AlterarClienteRequest {

	@JsonProperty("cliente")
	@NotNull(message = Constantes.CLIENTE_OBRIGATORIO)
	@Valid
	private AlterarClienteDTO detalheClienteDTO;

	public AlterarClienteRequest(AlterarClienteDTO alterarClienteDTO) {
		detalheClienteDTO = alterarClienteDTO;
	}

	public AlterarClienteRequest() {
	}

	public AlterarClienteDTO getDetalheClienteDTO() {
		return detalheClienteDTO;
	}

	public void setDetalheClienteDTO(AlterarClienteDTO detalheClienteDTO) {
		this.detalheClienteDTO = detalheClienteDTO;
	}

}
