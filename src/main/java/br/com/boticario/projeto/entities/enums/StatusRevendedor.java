package br.com.boticario.projeto.entities.enums;

public enum StatusRevendedor {
	
	ATIVO(1),
	INATIVO(2);
	
	private int code;
	
	private StatusRevendedor(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static StatusRevendedor valueOf(int code) {
		
		for(StatusRevendedor value : StatusRevendedor.values()) {
			if(value.getCode() == code ) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid OrderStaus code");
	}

}
