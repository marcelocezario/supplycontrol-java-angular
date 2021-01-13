package br.com.satsolucoes.supplycontrol.entities;

import java.io.Serializable;
import java.util.Set;

public class Vehicle implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String licensePlate;
	private Integer modelYear;
	private ModelVehicle modelVehicle;
	
	private Set<Supply> suplies;
	
	public Vehicle() {
	}

	public Vehicle(Long id, String licensePlate, Integer modelYear, ModelVehicle modelVehicle) {
		super();
		this.id = id;
		this.licensePlate = licensePlate;
		this.modelYear = modelYear;
		this.modelVehicle = modelVehicle;
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
	
	public ModelVehicle getModelVehicle() {
		return modelVehicle;
	}

	public void setModelVehicle(ModelVehicle modelVehicle) {
		this.modelVehicle = modelVehicle;
	}

	public Set<Supply> getSuplies() {
		return suplies;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
