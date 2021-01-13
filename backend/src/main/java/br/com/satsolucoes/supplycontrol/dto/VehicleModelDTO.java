package br.com.satsolucoes.supplycontrol.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import br.com.satsolucoes.supplycontrol.entities.Brand;
import br.com.satsolucoes.supplycontrol.entities.VehicleModel;

public class VehicleModelDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private Brand brand;
	private List<VehicleDTO> vehicles;
	
	public VehicleModelDTO() {
	}

	public VehicleModelDTO(Long id, String name, Brand brand) {
		super();
		this.id = id;
		this.name = name;
		this.brand = brand;
	}
	
	public VehicleModelDTO(VehicleModel entity) {
		id = entity.getId();
		name = entity.getName();
		brand = entity.getBrand();
		vehicles = entity.getVehicles().stream().map(x -> new VehicleDTO(x)).collect(Collectors.toList());
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

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public List<VehicleDTO> getVehicles() {
		return vehicles;
	}	
}
