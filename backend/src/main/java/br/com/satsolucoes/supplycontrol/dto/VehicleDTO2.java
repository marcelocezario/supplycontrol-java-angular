package br.com.satsolucoes.supplycontrol.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import br.com.satsolucoes.supplycontrol.entities.Vehicle;
import br.com.satsolucoes.supplycontrol.entities.VehicleModel;

public class VehicleDTO2 implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String licensePlate;
	private Integer modelYear;
	private Integer tankCapacity;
	private List<SupplyDTO2> supplies;
	
	public VehicleDTO2() {
	}

	public VehicleDTO2(Long id, String licensePlate, Integer modelYear, Integer tankCapacity) {
		super();
		this.id = id;
		this.licensePlate = licensePlate;
		this.modelYear = modelYear;
		this.tankCapacity = tankCapacity;
	}
	
	public VehicleDTO2(Vehicle entity) {
		id = entity.getId();
		licensePlate = entity.getLicensePlate();
		modelYear = entity.getModelYear();
		tankCapacity = entity.getTankCapacity();
		supplies = entity.getSupplies().stream().map(x -> new SupplyDTO2(x)).collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public Integer getModelYear() {
		return modelYear;
	}

	public void setModelYear(Integer modelYear) {
		this.modelYear = modelYear;
	}
	
	public Integer getTankCapacity() {
		return tankCapacity;
	}

	public void setTankCapacity(Integer tankCapacity) {
		this.tankCapacity = tankCapacity;
	}

	public List<SupplyDTO2> getSupplies() {
		return supplies;
	}
}
