package br.com.satsolucoes.supplycontrol.entities.enums;

public enum Fuel {
	
	ETHANOL(0, "Etanol"),
	GASOLINE(1, "Gasolina"),
	CNG(2, "GNV"),
	DIESEL_S10(3, "Diesel S10"),
	DIESEL_S500(4, "Diesel S500");
	
	private Integer cod;
	private String descricao;
	
	private Fuel(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Fuel toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (Fuel x : Fuel.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
