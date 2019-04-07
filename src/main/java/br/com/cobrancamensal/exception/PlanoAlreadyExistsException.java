package br.com.cobrancamensal.exception;

public class PlanoAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 2192349234124808955L;

	public PlanoAlreadyExistsException() {
		super("Plano já existente!");
	}

}
