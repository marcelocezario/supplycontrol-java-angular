package br.com.satsolucoes.supplycontrol.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.satsolucoes.supplycontrol.entities.enums.Fuel;

@Entity
@Table(name = "tb_supply")
public class Supply implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Instant moment;
	private Integer odometer;
	private Double litersFilled;
	private Double priceTotal;
	private boolean fullTank;
	private Fuel fuel;
	private Integer totalJourneyFromFullTank;
	private Double totalLitersWithTheJourney;

	@ManyToOne
	@JoinColumn(name = "vehicle_id")
	private Vehicle vehicle;

	public Supply() {
	}

	public Supply(Long id, Instant moment, Integer odometer, Double litersFilled, Double priceTotal, boolean fullTank,
			Fuel fuel, Integer totalJourneyFromFullTank, Double totalLitersWithTheJourney, Vehicle vehicle) {
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
		this.vehicle = vehicle;
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
		if (totalJourneyFromFullTank == 0 || totalLitersWithTheJourney == 0) {
			return 0.0;
		}
		return totalJourneyFromFullTank / totalLitersWithTheJourney;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
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
		Supply other = (Supply) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
