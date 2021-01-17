package br.com.satsolucoes.supplycontrol.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_vehicle")
public class Vehicle implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String licensePlate;
	private Integer modelYear;
	private Integer tankCapacity;

	@ManyToOne
	@JoinColumn(name = "modelVehicle_id", nullable = true)
	private VehicleModel vehicleModel;

	@OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Supply> supplies;

	public Vehicle() {
	}

	public Vehicle(Long id, String licensePlate, Integer modelYear, Integer tankCapacity, VehicleModel vehicleModel) {
		super();
		this.id = id;
		this.licensePlate = licensePlate;
		this.modelYear = modelYear;
		this.tankCapacity = tankCapacity;
		this.vehicleModel = vehicleModel;
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

	public VehicleModel getVehicleModel() {
		return vehicleModel;
	}

	public void setVehicleModel(VehicleModel vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	public Set<Supply> getSuplies() {
		return supplies;
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
