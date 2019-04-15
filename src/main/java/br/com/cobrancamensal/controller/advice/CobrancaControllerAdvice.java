package br.com.cobrancamensal.controller.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	Logger logger = LoggerFactory.getLogger(CobrancaControllerAdvice.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception exception) {
		logger.error(exception.getMessage(), exception);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(exception.getMessage()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException methodArgumentNotValidException) {
		logger.error(methodArgumentNotValidException.getBindingResult().getFieldError().getDefaultMessage(),
				methodArgumentNotValidException);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(
				methodArgumentNotValidException.getBindingResult().getFieldError().getDefaultMessage()));
	}

	@ExceptionHandler(ClienteNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleClienteNotFoundException(
			ClienteNotFoundException clienteNotFoundException) {
		logger.error(clienteNotFoundException.getMessage(), clienteNotFoundException);
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ErrorResponse(clienteNotFoundException.getMessage()));
	}

	@ExceptionHandler(PlanoNotFoundException.class)
	public ResponseEntity<ErrorResponse> handlePlanoNotFoundException(PlanoNotFoundException planoNotFoundException) {
		logger.error(planoNotFoundException.getMessage(), planoNotFoundException);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(planoNotFoundException.getMessage()));
	}

	@ExceptionHandler(PlanoDuplicadoException.class)
	public ResponseEntity<ErrorResponse> handlePlanoDuplicadoException(
			PlanoDuplicadoException planoDuplicadoException) {
		logger.error(planoDuplicadoException.getMessage(), planoDuplicadoException);
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(planoDuplicadoException.getMessage()));
	}

	@ExceptionHandler(ClienteDuplicadoException.class)
	public ResponseEntity<ErrorResponse> handleClienteDuplicadoException(
			ClienteDuplicadoException clienteDuplicadoException) {
		logger.error(clienteDuplicadoException.getMessage(), clienteDuplicadoException);
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(new ErrorResponse(clienteDuplicadoException.getMessage()));
	}

	@ExceptionHandler(ContratoDuplicadoException.class)
	public ResponseEntity<ErrorResponse> handleContratoDuplicadoException(
			ContratoDuplicadoException contratoDuplicadoException) {
		logger.error(contratoDuplicadoException.getMessage(), contratoDuplicadoException);
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(new ErrorResponse(contratoDuplicadoException.getMessage()));
	}

}
