package br.com.satsolucoes.supplycontrol.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.satsolucoes.supplycontrol.dto.SupplyDTO;
import br.com.satsolucoes.supplycontrol.entities.Supply;
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

	public Supply insert(Supply obj) {
		return repository.save(obj);
	}

	public void delete(Long id) {
		findById(id);
		repository.deleteById(id);
	}

	public Supply update(Supply obj) {
		Supply newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	public void updateData(Supply newObj, Supply obj) {
		newObj.setAverageConsumption(obj.getAverageConsumption());
		newObj.setFuel(obj.getFuel());
		newObj.setFullTank(obj.isFullTank());
		newObj.setLitersFilled(obj.getLitersFilled());
		newObj.setLiterValueOfFuel(obj.getLiterValueOfFuel());
		newObj.setMoment(obj.getMoment());
		newObj.setOdometer(obj.getOdometer());
		newObj.setVehicle(obj.getVehicle());
	}

	public Supply fromDTO(SupplyDTO objDTO) {
		return new Supply(objDTO.getId(), objDTO.getMoment(), objDTO.getOdometer(), objDTO.getLitersFilled(),
				objDTO.getLiterValueOfFuel(), objDTO.isFullTank(), objDTO.getFuel(),
				vehicleService.fromDTO(objDTO.getVehicle()));
	}
}
