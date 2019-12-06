package br.com.boticario.projeto.entities;

import java.io.Serializable;
import java.util.Calendar;

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
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private Calendar data;
	
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
	
	public Compra(Long id, String codigo, Double valor, Calendar data, CompraStatus compraStatus, Double porcentagemCashback, Double valorCashback, Revendedor revendedor) {
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

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
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


}
