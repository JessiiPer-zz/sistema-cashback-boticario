package br.com.boticario.projeto.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Login implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Email
	@NotBlank(message = "E-mail é de preenchimento obrigatório")
	private String email;
	@NotBlank
	@Size(min = 8, max = 100, message = "A senha é de preenchimento obrigatório e deve conter no mínimo 8 caracteres")
	private String senha;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

}
