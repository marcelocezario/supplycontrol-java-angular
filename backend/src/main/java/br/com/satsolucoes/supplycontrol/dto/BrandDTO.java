package br.com.satsolucoes.supplycontrol.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import br.com.satsolucoes.supplycontrol.entities.Brand;

public class BrandDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String imageUrl;
	private List<VehicleModelListDTO> vehicleModels;

	public BrandDTO() {
	}

	public BrandDTO(Long id, String name, String imageUrl) {
		super();
		this.id = id;
		this.name = name;
		this.imageUrl = imageUrl;
	}

	public BrandDTO(Brand entity) {
		id = entity.getId();
		name = entity.getName();
		imageUrl = entity.getImageUrl();
		vehicleModels = entity.getVehicleModels().stream().map(x -> new VehicleModelListDTO(x))
				.collect(Collectors.toList());
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<VehicleModelListDTO> getVehicleModels() {
		return vehicleModels;
	}
}
