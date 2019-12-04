package br.com.boticario.projeto.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CompraDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String codigo;
	private String valor;
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private String data;

	private String revendedor;
	
	@JsonProperty("porcentagem_cashback")
	private String porcentagemCashback;
	
	@JsonProperty("valor_cashback")
	private String valorCashback;
	
	@JsonProperty("status_compra")
	private String statusCompra;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getRevendedor() {
		return revendedor;
	}

	public void setRevendedor(String cpf) {
		this.revendedor = cpf;
	}

	public String getPorcentagemCashback() {
		return porcentagemCashback;
	}

	public void setPorcentagemCashback(String porcentagemCashback) {
		this.porcentagemCashback = porcentagemCashback;
	}

	public String getValorCashback() {
		return valorCashback;
	}

	public void setValorCashback(String valorCashback) {
		this.valorCashback = valorCashback;
	}

	public String getStatusCompra() {
		return statusCompra;
	}

	public void setStatusCompra(String statusCompra) {
		this.statusCompra = statusCompra;
	}

}
