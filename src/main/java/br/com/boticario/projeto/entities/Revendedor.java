package br.com.boticario.projeto.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.boticario.projeto.entities.enums.StatusRevendedor;

@Entity
@Table(name="tb_revendedor", uniqueConstraints = {@UniqueConstraint(name = "cpf", columnNames = "cpf")})
public class Revendedor implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Nome é de preenchimento obrigatório")
	private String nome;
	@CPF(message = "CPF inválido")
	@NotBlank
	private String cpf;
	@Email
	@NotBlank(message = "E-mail é de preenchimento obrigatório")
	private String email;
	@NotBlank
	@Size(min = 8, max = 100, message = "A senha é de preenchimento obrigatório e deve conter no mínimo 8 caracteres")
	private String senha;
	
	@JsonIgnore
	private Integer statusPerfilRevendedor;

	@OneToMany(mappedBy="revendedor")
	@JsonIgnore
	private List<Compra> compras = new ArrayList<>();
	
	public Revendedor() {}
	
	public Revendedor(Long id, String nome, String cpf, String email, String senha, Integer statusPerfilRevendedor) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		this.statusPerfilRevendedor = statusPerfilRevendedor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

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

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	public Long getId() {
		return id;
	}

	public StatusRevendedor getStatusPerfilRevendedor() {
		return StatusRevendedor.valueOf(statusPerfilRevendedor);
	}

	public void setStatusPerfilRevendedor(StatusRevendedor statusPerfilRevendedor) {
		if( statusPerfilRevendedor != null) {
			this.statusPerfilRevendedor = statusPerfilRevendedor.getCode();
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((compras == null) ? 0 : compras.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Revendedor other = (Revendedor) obj;
		if (compras == null) {
			if (other.compras != null)
				return false;
		} else if (!compras.equals(other.compras))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}


}
