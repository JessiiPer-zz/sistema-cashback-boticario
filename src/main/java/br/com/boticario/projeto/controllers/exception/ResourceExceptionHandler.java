package br.com.boticario.projeto.controllers.exception;


import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.core.JsonParseException;

import br.com.boticario.projeto.services.exception.DatabaseException;
import br.com.boticario.projeto.services.exception.ResourceNotFoundException;

@RestControllerAdvice
public class ResourceExceptionHandler{
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
		String error = "Recurso não encontrado";
		HttpStatus status = HttpStatus.NOT_FOUND;
		ErrorResponse err = new ErrorResponse(Instant.now(), status.value(),error, null, request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<ErrorResponse> dataBase(DatabaseException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ErrorResponse err = new ErrorResponse(Instant.now(), status.value(),e.getMessage(), null, request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> teste(MethodArgumentNotValidException ex,  HttpServletRequest request) {
        List<ObjectError> errors = getErrors(ex);
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse errorResponse = getErrorResponse(ex, errors, request);
        return ResponseEntity.status(status).body(errorResponse);
    }

	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<ErrorResponse> dataBase(NumberFormatException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String erro = "CPF Inválido";
		ErrorResponse err = new ErrorResponse(Instant.now(), status.value(),erro, null, request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ErrorResponse> dataBase(MissingServletRequestParameterException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String erro = "CPF é um campo obrigatório";
		ErrorResponse err = new ErrorResponse(Instant.now(), status.value(),erro, null, request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(JsonParseException.class)
	public ResponseEntity<ErrorResponse> dataBase(JsonParseException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String erro = "Json incorreto.";
		ErrorResponse err = new ErrorResponse(Instant.now(), status.value(),erro, null, request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	private ErrorResponse getErrorResponse(MethodArgumentNotValidException ex, List<ObjectError> errors, HttpServletRequest request) {
        return new ErrorResponse(Instant.now(), 400, "Requisição possui campos inválidos", errors, request.getRequestURI());
    }
	  
    private List<ObjectError> getErrors(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new ObjectError(error.getDefaultMessage(), error.getField(), error.getRejectedValue()))
                .collect(Collectors.toList());
    }
}

