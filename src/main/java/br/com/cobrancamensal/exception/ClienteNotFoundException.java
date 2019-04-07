package br.com.cobrancamensal.exception;

public class ClienteNotFoundException extends Exception {

	private static final long serialVersionUID = 3778835463491003963L;

	public ClienteNotFoundException() {
		super("Cliente não encontrado!");
	}

}
