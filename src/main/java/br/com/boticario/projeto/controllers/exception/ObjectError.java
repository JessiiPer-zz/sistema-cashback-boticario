package br.com.boticario.projeto.controllers.exception;

public class ObjectError {
	
    private final String mensagem;
    private final String campo;
    private final Object parâmetro;
    
	public ObjectError(String mensagem, String campo, Object parameter) {
		super();
		this.mensagem = mensagem;
		this.campo = campo;
		this.parâmetro = parameter;
	}

	public String getMessage() {
		return mensagem;
	}

	public String getField() {
		return campo;
	}

	public Object getParameter() {
		return parâmetro;
	}
	
}
