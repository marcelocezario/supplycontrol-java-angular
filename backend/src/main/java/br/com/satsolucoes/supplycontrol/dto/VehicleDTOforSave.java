package br.com.satsolucoes.supplycontrol.dto;

import java.io.Serializable;

import br.com.satsolucoes.supplycontrol.entities.Vehicle;
import br.com.satsolucoes.supplycontrol.entities.VehicleModel;

public class VehicleDTOforSave implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String licensePlate;
	private Integer modelYear;
	private Integer tankCapacity;
	private VehicleModelDTOforSave vehicleModel;
	
	public VehicleDTOforSave() {
	}

	public VehicleDTOforSave(Long id, String licensePlate, Integer modelYear, Integer tankCapacity, VehicleModel vehicleModel) {
		super();
		this.id = id;
		this.licensePlate = licensePlate;
		this.modelYear = modelYear;
		this.tankCapacity = tankCapacity;
		this.vehicleModel = new VehicleModelDTOforSave(vehicleModel);
	}
	
	public VehicleDTOforSave(Vehicle entity) {
		id = entity.getId();
		licensePlate = entity.getLicensePlate();
		modelYear = entity.getModelYear();
		tankCapacity = entity.getTankCapacity();
		vehicleModel = new VehicleModelDTOforSave(entity.getVehicleModel());
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

	public VehicleModelDTOforSave getVehicleModel() {
		return vehicleModel;
	}

	public void setVehicleModel(VehicleModel vehicleModel) {
		this.vehicleModel = new VehicleModelDTOforSave(vehicleModel);
	}
}
