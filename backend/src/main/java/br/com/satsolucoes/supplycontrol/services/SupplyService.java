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

	@Transactional
	public Supply insert(Supply obj) {

		obj.setVehicle(vehicleService.findById(obj.getVehicle().getId()));
		
		if (obj.getLitersFilled() <= obj.getVehicle().getTankCapacity()) {
			if(obj.isFullTank()) {
				Supply lastSupplyFullTank = findLastSupplyWithFullTank(obj);
				if(lastSupplyFullTank != null) {
					obj.setTotalJourneyFromFullTank(obj.getOdometer() - lastSupplyFullTank.getOdometer());
					obj.setTotalLitersWithTheJourney(sumLitersFilledInTheInterval(obj, lastSupplyFullTank));
				} else {
					obj.setTotalJourneyFromFullTank(0);
					obj.setTotalLitersWithTheJourney(0.0);
				}
			} else {
				obj.setTotalJourneyFromFullTank(0);
				obj.setTotalLitersWithTheJourney(0.0);
			}
			
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
	
	@Transactional
	public Supply update(Supply obj) {
		Supply newObj = findById(obj.getId());
		newObj.setVehicle(vehicleService.findById(obj.getVehicle().getId()));
		
		if (newObj.getLitersFilled() <= newObj.getVehicle().getTankCapacity()) {
			newObj.setMoment(obj.getMoment());
			newObj.setOdometer(obj.getOdometer());
			newObj.setLitersFilled(obj.getLitersFilled());
			newObj.setPriceTotal(obj.getPriceTotal());
			newObj.setFullTank(obj.isFullTank());
			newObj.setFuel(obj.getFuel());
			
			if(newObj.isFullTank()) {
				Supply lastSupplyFullTank = findLastSupplyWithFullTank(newObj);
				if(lastSupplyFullTank != null) {
					newObj.setTotalJourneyFromFullTank(newObj.getOdometer() - lastSupplyFullTank.getOdometer());
					newObj.setTotalLitersWithTheJourney(sumLitersFilledInTheInterval(newObj, lastSupplyFullTank));
				} else {
					newObj.setTotalJourneyFromFullTank(0);
					newObj.setTotalLitersWithTheJourney(0.0);
				}
			} else {
				newObj.setTotalJourneyFromFullTank(0);
				newObj.setTotalLitersWithTheJourney(0.0);
			}
			
			newObj = repository.save(newObj);
			checkSubsequentSuppliesFullTank(newObj);

			return newObj;
		} else {
			/*
			 * Mensagem de erro: Tanque menor que a quantidade abastecida
			 */
			return null;
		}
	}
	
	@Transactional
	public void delete(Long id) {
		findById(id);
		repository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public List<Supply> findAll() {
		return repository.findAll();
	}

	@Transactional(readOnly = true)
	public Supply findById(Long id) {
		Optional<Supply> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public List<Supply> findByMomentBetween(Instant initialDate, Instant finalDate) {
		return repository.findByMomentBetween(initialDate, finalDate);
	}

	@Transactional(readOnly = true)
	public List<Supply> findByVehicleOrderByOdometerDesc(Vehicle vehicle) {
		return repository.findByVehicleOrderByOdometerDesc(vehicle);
	}

	@Transactional(readOnly = true)
	public Supply findLastSupplyWithFullTank(Supply supply) {
		for (Supply x : findByVehicleOrderByOdometerDesc(supply.getVehicle())) {
			if (supply.getOdometer() > x.getOdometer() && x.isFullTank()) {
				return x;
			}
		}
		return null;
	}
	
	@Transactional(readOnly = true)
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

	public void checkSubsequentSuppliesFullTank(Supply obj) {
		Supply subSupply = findNextSupplyWithFullTank(obj);
		if (subSupply != null) {
			update(subSupply);
		}
	}
	
	@Transactional(readOnly = true)
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
	
	public Supply fromDTO(SupplyDTO objDTO) {
		return new Supply(objDTO.getId(), objDTO.getMoment(), objDTO.getOdometer(), objDTO.getLitersFilled(),
				objDTO.getPriceTotal(), objDTO.isFullTank(), objDTO.getFuel(), objDTO.getTotalJourneyFromFullTank(),
				objDTO.getTotalLitersWithTheJourney(), vehicleService.fromDTO(objDTO.getVehicle()));
	}
}