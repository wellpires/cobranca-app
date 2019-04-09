package br.com.cobrancamensal.exception;

public class ClienteDuplicadoException extends Exception {

	private static final long serialVersionUID = -5937808175190249220L;

	public ClienteDuplicadoException() {
		super("CPF já existente!");
	}

}
