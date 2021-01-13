package br.com.satsolucoes.supplycontrol.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import br.com.satsolucoes.supplycontrol.entities.Vehicle;
import br.com.satsolucoes.supplycontrol.entities.VehicleModel;

public class VehicleDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String licensePlate;
	private Integer modelYear;
	private VehicleModel vehicleModel;
	private List<SupplyDTO> suplies;
	
	public VehicleDTO() {
	}

	public VehicleDTO(Long id, String licensePlate, Integer modelYear, VehicleModel vehicleModel) {
		super();
		this.id = id;
		this.licensePlate = licensePlate;
		this.modelYear = modelYear;
		this.vehicleModel = vehicleModel;
	}
	
	public VehicleDTO(Vehicle entity) {
		id = entity.getId();
		licensePlate = entity.getLicensePlate();
		modelYear = entity.getModelYear();
		vehicleModel = entity.getVehicleModel();
		suplies = entity.getSuplies().stream().map(x -> new SupplyDTO(x)).collect(Collectors.toList());
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

	public VehicleModel getVehicleModel() {
		return vehicleModel;
	}

	public void setVehicleModel(VehicleModel vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	public List<SupplyDTO> getSuplies() {
		return suplies;
	}
}
