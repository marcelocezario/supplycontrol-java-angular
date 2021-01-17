package br.com.satsolucoes.supplycontrol.dto;

import java.io.Serializable;

import br.com.satsolucoes.supplycontrol.entities.Brand;
import br.com.satsolucoes.supplycontrol.entities.VehicleModel;

public class VehicleModelDTOforSave implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private BrandDTOforSave brand;

	public VehicleModelDTOforSave() {
	}

	public VehicleModelDTOforSave(Long id, String name, Brand brand) {
		super();
		this.id = id;
		this.name = name;
		this.brand = new BrandDTOforSave(brand);
	}

	public VehicleModelDTOforSave(VehicleModel entity) {
		id = entity.getId();
		name = entity.getName();
		brand = new BrandDTOforSave(entity.getBrand());
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

	public BrandDTOforSave getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = new BrandDTOforSave(brand);
	}
}
