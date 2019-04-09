package br.com.cobrancamensal.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.cobrancamensal.exception.ClienteDuplicadoException;
import br.com.cobrancamensal.exception.ClienteNotFoundException;
import br.com.cobrancamensal.exception.ContratoDuplicadoException;
import br.com.cobrancamensal.exception.PlanoDuplicadoException;
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

	@ExceptionHandler(PlanoDuplicadoException.class)
	public ResponseEntity<ErrorResponse> handlePlanoDuplicadoException(
			PlanoDuplicadoException planoDuplicadoException) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(planoDuplicadoException.getMessage()));
	}

	@ExceptionHandler(ClienteDuplicadoException.class)
	public ResponseEntity<ErrorResponse> handleClienteDuplicadoException(
			ClienteDuplicadoException clienteDuplicadoException) {
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(new ErrorResponse(clienteDuplicadoException.getMessage()));
	}

	@ExceptionHandler(ContratoDuplicadoException.class)
	public ResponseEntity<ErrorResponse> handleContratoDuplicadoException(
			ContratoDuplicadoException contratoDuplicadoException) {
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(new ErrorResponse(contratoDuplicadoException.getMessage()));
	}

}
