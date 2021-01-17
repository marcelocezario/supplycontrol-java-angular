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
	private Double totalTraveled;
	
	private List<VehicleModelDTOforList> vehicleModels;

	public BrandDTO() {
	}

	public BrandDTO(Long id, String name, String imageUrl, Double totalTraveled) {
		super();
		this.id = id;
		this.name = name;
		this.imageUrl = imageUrl;
		this.totalTraveled = totalTraveled;
	}

	public BrandDTO(Brand entity) {
		id = entity.getId();
		name = entity.getName();
		imageUrl = entity.getImageUrl();
		totalTraveled = entity.getTotalTraveled();
		vehicleModels = entity.getVehicleModels().stream().map(x -> new VehicleModelDTOforList(x))
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
	
	public Double getTotalTraveled() {
		return totalTraveled;
	}

	public void setTotalTraveled(Double totalTraveled) {
		this.totalTraveled = totalTraveled;
	}

	public List<VehicleModelDTOforList> getVehicleModels() {
		return vehicleModels;
	}
}
