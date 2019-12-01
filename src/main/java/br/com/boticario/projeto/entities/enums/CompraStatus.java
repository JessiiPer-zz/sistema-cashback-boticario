package br.com.boticario.projeto.entities.enums;

public enum CompraStatus {
	
	WAITING_PAYMENT(1),
	PAID(2),
	SHIPPED(3),
	DELIVERED(4),
	CANCELED(5);
	
	private int code;
	
	private CompraStatus(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static CompraStatus valueOf(int code) {
		
		for(CompraStatus value : CompraStatus.values()) {
			if(value.getCode() == code ) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid OrderStaus code");
	}

}
