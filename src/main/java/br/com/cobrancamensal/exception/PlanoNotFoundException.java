package br.com.cobrancamensal.exception;

public class PlanoNotFoundException extends Exception {

	private static final long serialVersionUID = 5000976026633245839L;

	public PlanoNotFoundException() {
		super("Plano não encontrado!");
	}

}
