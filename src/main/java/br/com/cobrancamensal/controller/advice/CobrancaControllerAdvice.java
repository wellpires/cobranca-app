package br.com.cobrancamensal.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.cobrancamensal.exception.ClienteAlreadyExistsException;
import br.com.cobrancamensal.exception.ClienteNotFoundException;
import br.com.cobrancamensal.exception.ContratoAlreadyExistsException;
import br.com.cobrancamensal.exception.PlanoAlreadyExistsException;
import br.com.cobrancamensal.exception.PlanoNotFoundException;
import br.com.cobrancamensal.response.ErrorResponse;

@RestControllerAdvice
public class CobrancaControllerAdvice {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception exception) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(exception.getMessage()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException methodArgumentNotValidException) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(
				methodArgumentNotValidException.getBindingResult().getFieldError().getDefaultMessage()));
	}

	@ExceptionHandler(ClienteNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleClienteNotFoundException(
			ClienteNotFoundException clienteNotFoundException) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ErrorResponse(clienteNotFoundException.getMessage()));
	}

	@ExceptionHandler(PlanoNotFoundException.class)
	public ResponseEntity<ErrorResponse> handlePlanoNotFoundException(PlanoNotFoundException planoNotFoundException) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(planoNotFoundException.getMessage()));
	}

	@ExceptionHandler(PlanoAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handlePlanoAlreadyExistsException(
			PlanoAlreadyExistsException planoAlreadyExistsException) {
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(new ErrorResponse(planoAlreadyExistsException.getMessage()));
	}

	@ExceptionHandler(ClienteAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleClienteAlreadyExistsException(
			ClienteAlreadyExistsException clienteAlreadyExistsException) {
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(new ErrorResponse(clienteAlreadyExistsException.getMessage()));
	}

	@ExceptionHandler(ContratoAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleContratoAlreadyExistsException(
			ContratoAlreadyExistsException contratoAlreadyExistsException) {
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(new ErrorResponse(contratoAlreadyExistsException.getMessage()));
	}

}
