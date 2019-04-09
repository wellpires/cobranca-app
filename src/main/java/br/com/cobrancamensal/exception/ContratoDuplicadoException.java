package br.com.cobrancamensal.exception;

public class ContratoDuplicadoException extends Exception {

	private static final long serialVersionUID = 631178886051214104L;

	public ContratoDuplicadoException(String cpf, String nomePlano) {
		super(String.format("O plano %s já foi contratado pelo CPF %s!", nomePlano, cpf));
	}

}
