package br.com.cobrancamensal.exception;

public class PlanoDuplicadoException extends Exception {

	private static final long serialVersionUID = 2192349234124808955L;

	public PlanoDuplicadoException() {
		super("Plano já existente!");
	}

}
