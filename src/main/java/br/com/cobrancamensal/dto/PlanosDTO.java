package br.com.cobrancamensal.dto;

import java.util.List;

public class PlanosDTO {

	private List<PlanoDTO> planos;

	public PlanosDTO(List<PlanoDTO> planos) {
		this.planos = planos;
	}

	public PlanosDTO() {
	}

	public List<PlanoDTO> getPlanos() {
		return planos;
	}

	public void setPlanos(List<PlanoDTO> planos) {
		this.planos = planos;
	}

}
