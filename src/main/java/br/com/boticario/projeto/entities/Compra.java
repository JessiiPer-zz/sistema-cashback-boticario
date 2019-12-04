package br.com.boticario.projeto.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.boticario.projeto.entities.enums.CompraStatus;

@Entity
@Table(name="tb_compra")
public class Compra implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String codigo;
	@NotNull
	private Double valor;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Date data;
	
	@ManyToOne
	@JoinColumn(name="revendedor_cpf", referencedColumnName = "cpf")
	private Revendedor revendedor;
	
	@JsonIgnore
	private Integer compraStatus;
	
	@JsonProperty("porcentagem_cashback")
	private Double porcentagemCashback;
	
	@JsonProperty("valor_cashback")
	private Double valorCashback;

	public Compra() {}
	
	public Compra(Long id, String codigo, Double valor, Date data, CompraStatus compraStatus, Double porcentagemCashback, Double valorCashback, Revendedor revendedor) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.valor = valor;
		this.data = data;
		setCompraStatus(compraStatus);
		this.porcentagemCashback = porcentagemCashback;
		this.valorCashback = valorCashback;
		this.revendedor = revendedor;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getRevendedor() {
		return revendedor.getCpf();
	}

	public void setRevendedor(Revendedor revendedor) {
		this.revendedor = revendedor;
	}

	public Long getId() {
		return id;
	}

	public CompraStatus getCompraStatus() {
		return CompraStatus.valueOf(compraStatus);
	}

	public void setCompraStatus(CompraStatus compraStatus) {
		if( compraStatus != null) {
			this.compraStatus = compraStatus.getCode();
		}
	}
	
	public Double getPorcentagemCashback() {
		return porcentagemCashback;
	}

	public void setPorcentagemCashback(Double porcentagemCashback) {
		this.porcentagemCashback = porcentagemCashback;
	}

	public Double getValorCashback() {
		return valorCashback;
	}

	public void setValorCashback(Double valorCashback) {
		this.valorCashback = valorCashback;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((compraStatus == null) ? 0 : compraStatus.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((revendedor == null) ? 0 : revendedor.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
		Compra other = (Compra) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (compraStatus == null) {
			if (other.compraStatus != null)
				return false;
		} else if (!compraStatus.equals(other.compraStatus))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (revendedor == null) {
			if (other.revendedor != null)
				return false;
		} else if (!revendedor.equals(other.revendedor))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

}
