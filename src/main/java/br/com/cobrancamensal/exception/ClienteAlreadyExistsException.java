package br.com.cobrancamensal.exception;

public class ClienteAlreadyExistsException extends Exception {

	private static final long serialVersionUID = -5937808175190249220L;

	public ClienteAlreadyExistsException() {
		super("CPF já existente!");
	}

}
