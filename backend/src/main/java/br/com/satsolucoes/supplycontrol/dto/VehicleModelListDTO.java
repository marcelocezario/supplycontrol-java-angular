package br.com.satsolucoes.supplycontrol.dto;

import java.io.Serializable;

import br.com.satsolucoes.supplycontrol.entities.VehicleModel;

public class VehicleModelListDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	
	public VehicleModelListDTO() {
	}

	public VehicleModelListDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public VehicleModelListDTO(VehicleModel entity) {
		id = entity.getId();
		name = entity.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
