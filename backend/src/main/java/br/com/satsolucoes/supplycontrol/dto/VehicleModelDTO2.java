package br.com.satsolucoes.supplycontrol.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import br.com.satsolucoes.supplycontrol.entities.Brand;
import br.com.satsolucoes.supplycontrol.entities.VehicleModel;

public class VehicleModelDTO2 implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private List<VehicleDTO2> vehicles;

	public VehicleModelDTO2() {
	}

	public VehicleModelDTO2(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public VehicleModelDTO2(VehicleModel entity) {
		id = entity.getId();
		name = entity.getName();
		vehicles = entity.getVehicles().stream().map(x -> new VehicleDTO2(x)).collect(Collectors.toList());
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

	public List<VehicleDTO2> getVehicles() {
		return vehicles;
	}
}
