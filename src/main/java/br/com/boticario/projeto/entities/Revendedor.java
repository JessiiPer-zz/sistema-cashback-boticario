package br.com.boticario.projeto.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
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
	@NotBlank(message = "O campo CPF não pode estar nulo")
	private String cpf;
	@Email(message= "E-mail inválido")
	@NotBlank(message = "E-mail é de preenchimento obrigatório")
	@Column(unique=true)
	private String email;
	@NotBlank(message = "A senha não pode ser nula ou vazia")
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

}
