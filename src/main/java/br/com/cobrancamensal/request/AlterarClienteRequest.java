package br.com.cobrancamensal.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.cobrancamensal.dto.AlterarClienteDTO;

public class AlterarClienteRequest {

	@JsonProperty("cliente")
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
