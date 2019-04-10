package br.com.cobrancamensal.response;

import java.util.List;

import br.com.cobrancamensal.dto.PlanoDTO;

public class PlanosResponse {

	private List<PlanoDTO> planos;

	public PlanosResponse(List<PlanoDTO> planos) {
		this.planos = planos;
	}

	public PlanosResponse() {
	}

	public List<PlanoDTO> getPlanos() {
		return planos;
	}

	public void setPlanos(List<PlanoDTO> planos) {
		this.planos = planos;
	}

}
