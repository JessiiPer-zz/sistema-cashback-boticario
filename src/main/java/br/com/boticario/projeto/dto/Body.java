package br.com.boticario.projeto.dto;

import java.io.Serializable;

public class Body implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer credit;
	
	public Integer getCredit() {
		return credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}



}
