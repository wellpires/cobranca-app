package br.com.cobrancamensal.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.cobrancamensal.dto.DetalheContratoDTO;

public class DetalheContratoResponse {

	@JsonProperty("contrato")
	private DetalheContratoDTO detalheContratoDTO;

	public DetalheContratoResponse(DetalheContratoDTO detalheContratoDTO) {
		this.detalheContratoDTO = detalheContratoDTO;
	}

	public DetalheContratoResponse() {
	}

	public DetalheContratoDTO getDetalheContratoDTO() {
		return detalheContratoDTO;
	}

	public void setDetalheContratoDTO(DetalheContratoDTO detalheContratoDTO) {
		this.detalheContratoDTO = detalheContratoDTO;
	}

}
