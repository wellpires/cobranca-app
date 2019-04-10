package br.com.cobrancamensal.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.cobrancamensal.dto.ContratoDTO;

public class ContratosResponse {

	@JsonProperty("contratos")
	private List<ContratoDTO> contratoDTOs;

	public ContratosResponse() {
	}

	public ContratosResponse(List<ContratoDTO> contratoDTOs) {
		this.contratoDTOs = contratoDTOs;
	}

	public List<ContratoDTO> getContratoDTOs() {
		return contratoDTOs;
	}

	public void setContratoDTOs(List<ContratoDTO> contratoDTOs) {
		this.contratoDTOs = contratoDTOs;
	}

}
