package br.com.cobrancamensal.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.cobrancamensal.dto.DetalheClienteDTO;

public class DetalheClienteResponse {

	@JsonProperty("cliente")
	private DetalheClienteDTO detalheClienteDTO;

	public DetalheClienteResponse(DetalheClienteDTO detalheClienteDTO) {
		this.detalheClienteDTO = detalheClienteDTO;
	}

	public DetalheClienteResponse() {
	}

	public DetalheClienteDTO getDetalheClienteDTO() {
		return detalheClienteDTO;
	}

	public void setDetalheClienteDTO(DetalheClienteDTO detalheClienteDTO) {
		this.detalheClienteDTO = detalheClienteDTO;
	}

}
