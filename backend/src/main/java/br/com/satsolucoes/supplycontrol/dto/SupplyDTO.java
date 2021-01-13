package br.com.satsolucoes.supplycontrol.dto;

import java.io.Serializable;
import java.time.Instant;

import br.com.satsolucoes.supplycontrol.entities.Supply;
import br.com.satsolucoes.supplycontrol.entities.Vehicle;
import br.com.satsolucoes.supplycontrol.entities.enums.Fuel;

public class SupplyDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Instant moment;
	private Integer odometer;
	private Double literValueOfFuel;
	private boolean fullTank;
	private Double averageConsumption;
	private Fuel fuel;
	private Vehicle vehicle;
	
	public SupplyDTO() {
	}

	public SupplyDTO(Long id, Instant moment, Integer odometer, Double literValueOfFuel, boolean fullTank,
			Double averageConsumption, Fuel fuel, Vehicle vehicle) {
		super();
		this.id = id;
		this.moment = moment;
		this.odometer = odometer;
		this.literValueOfFuel = literValueOfFuel;
		this.fullTank = fullTank;
		this.averageConsumption = averageConsumption;
		this.fuel = fuel;
		this.vehicle = vehicle;
	}
	
	public SupplyDTO(Supply entity) {
		id = entity.getId();
		moment = entity.getMoment();
		odometer = entity.getOdometer();
		literValueOfFuel = entity.getLiterValueOfFuel();
		fullTank = entity.isFullTank();
		averageConsumption = entity.getAverageConsumption();
		fuel = entity.getFuel();
		vehicle = entity.getVehicle();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public Integer getOdometer() {
		return odometer;
	}

	public void setOdometer(Integer odometer) {
		this.odometer = odometer;
	}

	public Double getLiterValueOfFuel() {
		return literValueOfFuel;
	}

	public void setLiterValueOfFuel(Double literValueOfFuel) {
		this.literValueOfFuel = literValueOfFuel;
	}

	public boolean isFullTank() {
		return fullTank;
	}

	public void setFullTank(boolean fullTank) {
		this.fullTank = fullTank;
	}

	public Double getAverageConsumption() {
		return averageConsumption;
	}

	public void setAverageConsumption(Double averageConsumption) {
		this.averageConsumption = averageConsumption;
	}

	public Fuel getFuel() {
		return fuel;
	}

	public void setFuel(Fuel fuel) {
		this.fuel = fuel;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
}
