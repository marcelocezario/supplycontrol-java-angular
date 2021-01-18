package br.com.satsolucoes.supplycontrol.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import br.com.satsolucoes.supplycontrol.entities.Brand;
import br.com.satsolucoes.supplycontrol.entities.VehicleModel;

public class VehicleModelDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private BrandDTO brand;

	public VehicleModelDTO() {
	}

	public VehicleModelDTO(Long id, String name, Brand brand) {
		super();
		this.id = id;
		this.name = name;
		this.brand = new BrandDTO(brand);
	}

	public VehicleModelDTO(VehicleModel entity) {
		id = entity.getId();
		name = entity.getName();
		brand = new BrandDTO(entity.getBrand());
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

	public BrandDTO getBrand() {
		return brand;
	}

	public void setBrand(BrandDTO brand) {
		this.brand = brand;
	}
}
