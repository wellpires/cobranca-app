package br.com.cobrancamensal.exception;

public class ContratoNotFoundException extends Exception {

	private static final long serialVersionUID = 3702004479546234313L;

	public ContratoNotFoundException() {
		super("Contrato não encontrado!");
	}

}
