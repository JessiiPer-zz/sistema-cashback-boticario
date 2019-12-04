package br.com.boticario.projeto.dto;

import java.io.Serializable;

public class AcumuloCashbackDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer statusCode;
	
	private Body body;

	
	public AcumuloCashbackDTO() {}
	public AcumuloCashbackDTO(Integer statusCode, Body body) {
		super();
		this.statusCode = statusCode;
		this.body = body;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

}
