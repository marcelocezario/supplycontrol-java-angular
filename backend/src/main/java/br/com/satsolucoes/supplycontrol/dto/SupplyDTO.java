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
	private Double litersFilled;
	private Double priceTotal;
	private boolean fullTank;
	private Fuel fuel;
	private Integer totalJourneyFromFullTank;
	private Double totalLitersWithTheJourney;
	private Double averageConsumption;
	private VehicleDTO vehicle;
	
	public SupplyDTO() {
	}
	
	public SupplyDTO(Long id, Instant moment, Integer odometer, Double litersFilled, Double priceTotal,
			boolean fullTank, Fuel fuel, Integer totalJourneyFromFullTank, Double totalLitersWithTheJourney,
			Vehicle vehicle, Double averageConsumption) {
		super();
		this.id = id;
		this.moment = moment;
		this.odometer = odometer;
		this.litersFilled = litersFilled;
		this.priceTotal = priceTotal;
		this.fullTank = fullTank;
		this.fuel = fuel;
		this.totalJourneyFromFullTank = totalJourneyFromFullTank;
		this.totalLitersWithTheJourney = totalLitersWithTheJourney;
		this.averageConsumption = averageConsumption;
		this.vehicle = new VehicleDTO(vehicle);
	}
	
	public SupplyDTO(Supply entity) {
		id = entity.getId();
		moment = entity.getMoment();
		odometer = entity.getOdometer();
		litersFilled = entity.getLitersFilled();
		priceTotal = entity.getPriceTotal();
		fullTank = entity.isFullTank();
		fuel = entity.getFuel();
		totalJourneyFromFullTank = entity.getTotalJourneyFromFullTank();
		totalLitersWithTheJourney = entity.getTotalLitersWithTheJourney();
		averageConsumption = entity.getAverageConsumption();
		vehicle = new VehicleDTO(entity.getVehicle());
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

	public Double getLitersFilled() {
		return litersFilled;
	}

	public void setLitersFilled(Double litersFilled) {
		this.litersFilled = litersFilled;
	}

	public Double getPriceTotal() {
		return priceTotal;
	}

	public void setPriceTotal(Double priceTotal) {
		this.priceTotal = priceTotal;
	}

	public boolean isFullTank() {
		return fullTank;
	}

	public void setFullTank(boolean fullTank) {
		this.fullTank = fullTank;
	}

	public Fuel getFuel() {
		return fuel;
	}

	public void setFuel(Fuel fuel) {
		this.fuel = fuel;
	}

	public Integer getTotalJourneyFromFullTank() {
		return totalJourneyFromFullTank;
	}

	public void setTotalJourneyFromFullTank(Integer totalJourneyFromFullTank) {
		this.totalJourneyFromFullTank = totalJourneyFromFullTank;
	}

	public Double getTotalLitersWithTheJourney() {
		return totalLitersWithTheJourney;
	}

	public void setTotalLitersWithTheJourney(Double totalLitersWithTheJourney) {
		this.totalLitersWithTheJourney = totalLitersWithTheJourney;
	}

	public Double getAverageConsumption() {
		return averageConsumption;
	}

	public void setAverageConsumption(Double averageConsumption) {
		this.averageConsumption = averageConsumption;
	}

	public VehicleDTO getVehicle() {
		return vehicle;
	}

	public void setVehicle(VehicleDTO vehicle) {
		this.vehicle = vehicle;
	}
}
