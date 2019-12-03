package br.com.boticario.projeto.controllers.exception;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ErrorResponse {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant timestamp;
	private final int code;
	private final String status;
	private String path;
	private final List<ObjectError> errors;

	public ErrorResponse(Instant timestamp, int code, String status,
			List<ObjectError> errors2, String path) {
		super();
		this.timestamp = timestamp;
		this.code = code;
		this.status = status;
		this.errors = errors2;
		this.path = path;

	}

	public String getStatus() {
		return status;
	}

	public List<ObjectError> getErrors() {
		return errors;
	}

	public int getCode() {
		return code;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
