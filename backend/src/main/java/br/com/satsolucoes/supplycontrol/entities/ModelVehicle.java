package br.com.satsolucoes.supplycontrol.entities;

import java.io.Serializable;
import java.util.Set;

public class ModelVehicle implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private Brand brand;
	
	private Set<Vehicle> vehicles;
	
	public ModelVehicle() {
	}

	public ModelVehicle(Long id, String name, Brand brand) {
		super();
		this.id = id;
		this.name = name;
		this.brand = brand;
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

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Set<Vehicle> getVehicles() {
		return vehicles;
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
		ModelVehicle other = (ModelVehicle) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
