package br.com.cobrancamensal.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.cobrancamensal.dto.DetalhePlanoDTO;

public class DetalhePlanoResponse {

	@JsonProperty("plano")
	private DetalhePlanoDTO detalhePlanoDTO;

	public DetalhePlanoDTO getDetalhePlanoDTO() {
		return detalhePlanoDTO;
	}

	public void setDetalhePlanoDTO(DetalhePlanoDTO detalhePlanoDTO) {
		this.detalhePlanoDTO = detalhePlanoDTO;
	}

}
