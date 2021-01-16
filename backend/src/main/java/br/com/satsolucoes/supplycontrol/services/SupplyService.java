package br.com.satsolucoes.supplycontrol.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.satsolucoes.supplycontrol.dto.SupplyDTO;
import br.com.satsolucoes.supplycontrol.entities.Supply;
import br.com.satsolucoes.supplycontrol.entities.Vehicle;
import br.com.satsolucoes.supplycontrol.repositories.SupplyRepository;
import br.com.satsolucoes.supplycontrol.services.exceptions.ResourceNotFoundException;

@Service
public class SupplyService {

	@Autowired
	private SupplyRepository repository;

	@Autowired
	private VehicleService vehicleService;

	@Transactional(readOnly = true)
	public List<Supply> findAll() {
		return repository.findAll();
	}

	@Transactional(readOnly = true)
	public Supply findById(Long id) {
		Optional<Supply> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	@Transactional(readOnly = true)
	public List<Supply> findByVehicleOrderByOdometerDesc(Vehicle vehicle) {
		return repository.findByVehicleOrderByOdometerDesc(vehicle);
	}

	public Supply findLastSupplyWithFullTank(Supply supply) {
		for (Supply x : findByVehicleOrderByOdometerDesc(supply.getVehicle())) {
			if (supply.getOdometer() > x.getOdometer() && x.isFullTank()) {
				return x;
			}
		}
		return null;
	}

	public Supply findNextSupplyWithFullTank(Supply supply) {
		Supply obj = null;
		for (Supply x : findByVehicleOrderByOdometerDesc(supply.getVehicle())) {
			if (supply.getOdometer() < x.getOdometer() && x.isFullTank()) {
				if (obj == null) {
					obj = x;
				} else if (obj.getOdometer() > x.getOdometer()) {
					obj = x;
				}
			}
		}
		return obj;
	}

	public Double sumLitersFilledInTheInterval(Supply supply, Supply lastSupplyFullTank) {
		Double liters = repository.sumLitersFilledInTheInterval(supply.getVehicle(), lastSupplyFullTank.getOdometer(),
				supply.getOdometer());
		if (liters != null) {
			liters += supply.getLitersFilled();
		} else {
			liters = supply.getLitersFilled();
		}
		return liters;
	}

	public Supply insert(Supply obj) {
		obj.setVehicle(vehicleService.findById(obj.getVehicle().getId()));

		if (obj.getLiterValueOfFuel() <= obj.getVehicle().getTankCapacity()) {
			obj.setAverageConsumption(calculateAverageConsumption(obj));
			obj = repository.save(obj);
			checkSubsequentSuppliesFullTank(obj);
			return obj;
		} else {
			/*
			 * Mensagem de erro: Tanque menor que a quantidade abastecida
			 */
			return null;
		}
	}

	public void delete(Long id) {
		findById(id);
		repository.deleteById(id);
	}

	public Supply update(Supply obj) {
		Supply newObj = findById(obj.getId());
		obj.setVehicle(vehicleService.findById(obj.getVehicle().getId()));
		if (obj.getLiterValueOfFuel() <= obj.getVehicle().getTankCapacity()) {
			updateData(newObj, obj);
			newObj = repository.save(newObj);
			checkSubsequentSuppliesFullTank(newObj);
		}
		return newObj;
	}

	public void updateData(Supply newObj, Supply obj) {
		newObj.setAverageConsumption(obj.getAverageConsumption());
		newObj.setFuel(obj.getFuel());
		newObj.setFullTank(obj.isFullTank());
		newObj.setLiterValueOfFuel(obj.getLiterValueOfFuel());
		newObj.setMoment(obj.getMoment());
		newObj.setOdometer(obj.getOdometer());
		newObj.setVehicle(obj.getVehicle());
		if (newObj.isFullTank()) {
			newObj.setAverageConsumption(calculateAverageConsumption(newObj));
		} else {
			newObj.setAverageConsumption(0.0);
		}
	}

	public Supply fromDTO(SupplyDTO objDTO) {
		return new Supply(objDTO.getId(), objDTO.getMoment(), objDTO.getOdometer(), objDTO.getLitersFilled(),
				objDTO.getLiterValueOfFuel(), objDTO.isFullTank(), objDTO.getFuel(),
				vehicleService.fromDTO(objDTO.getVehicle()));
	}

	public Double calculateAverageConsumption(Supply obj) {
		if (obj.isFullTank()) {
			Supply lastSupplyFullTank = findLastSupplyWithFullTank(obj);
			if (lastSupplyFullTank != null) {
				Double totalLiters = sumLitersFilledInTheInterval(obj, lastSupplyFullTank);
				return (obj.getOdometer() - lastSupplyFullTank.getOdometer()) / totalLiters;
			}
		}
		return 0.0;
	}

	public void checkSubsequentSuppliesFullTank(Supply obj) {
		Supply subSupply = findNextSupplyWithFullTank(obj);
		if (subSupply != null) {
			update(subSupply);
		}
	}

	public List<Supply> findByMomentBetween(Instant initialDate, Instant finalDate) {
		return repository.findByMomentBetween(initialDate, finalDate);
	}

}