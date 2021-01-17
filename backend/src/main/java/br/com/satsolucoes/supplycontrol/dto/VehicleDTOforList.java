package br.com.satsolucoes.supplycontrol.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import br.com.satsolucoes.supplycontrol.entities.Vehicle;
import br.com.satsolucoes.supplycontrol.entities.VehicleModel;

public class VehicleDTOforList implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String licensePlate;
	private Integer modelYear;
	private Integer tankCapacity;
	private List<SupplyDTOforList> supplies;
	
	public VehicleDTOforList() {
	}

	public VehicleDTOforList(Long id, String licensePlate, Integer modelYear, Integer tankCapacity) {
		super();
		this.id = id;
		this.licensePlate = licensePlate;
		this.modelYear = modelYear;
		this.tankCapacity = tankCapacity;
	}
	
	public VehicleDTOforList(Vehicle entity) {
		id = entity.getId();
		licensePlate = entity.getLicensePlate();
		modelYear = entity.getModelYear();
		tankCapacity = entity.getTankCapacity();
		supplies = entity.getSupplies().stream().map(x -> new SupplyDTOforList(x)).collect(Collectors.toList());
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

	public List<SupplyDTOforList> getSupplies() {
		return supplies;
	}
}
