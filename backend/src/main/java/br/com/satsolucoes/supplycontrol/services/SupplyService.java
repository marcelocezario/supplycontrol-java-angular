package br.com.satsolucoes.supplycontrol.services;

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
		// return repository.findAll();
		Vehicle v = vehicleService.findById(1L);

		return repository.teste(v, 5000);
	}

	@Transactional(readOnly = true)
	public Supply findById(Long id) {
		Optional<Supply> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Supply insert(Supply obj) {

		if (obj.getLiterValueOfFuel() < obj.getVehicle().getTankCapacity()) {
			obj.setAverageConsumption(calculateAverageConsumption(obj));
			obj = repository.save(obj);
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
		updateData(newObj, obj);
		newObj = repository.save(newObj);

		checkSubsequentSuppliesFullTank(newObj);

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

		newObj.setAverageConsumption(calculateAverageConsumption(newObj));
	}

	public Supply fromDTO(SupplyDTO objDTO) {
		return new Supply(objDTO.getId(), objDTO.getMoment(), objDTO.getOdometer(), objDTO.getLitersFilled(),
				objDTO.getLiterValueOfFuel(), objDTO.isFullTank(), objDTO.getFuel(),
				vehicleService.fromDTO(objDTO.getVehicle()));
	}

	public Double calculateAverageConsumption(Supply obj) {
		if (obj.isFullTank()) {
			/*
			 * Aqui preciso do último abastecimento com tanque cheio antes do abastecimento
			 * que está sendo cadastrado e não estou conseguindo retornar o objeto que tenha
			 * o maior valor de "odometer" atendendo as condições de ser menor que o "supply"
			 * que está sendo cadastrado e verdadeiro para "fullTank"
			 */
			Supply lastSupplyFullTank = repository.findLastSupplyWithFullTank(obj.getVehicle(), obj.getOdometer());
			
			if (lastSupplyFullTank != null) {
				Double totalLiters = repository.sumLitersFilledInTheInterval(obj.getVehicle(),
						lastSupplyFullTank.getOdometer(), obj.getOdometer());
				if (totalLiters != null) {
					totalLiters += lastSupplyFullTank.getLitersFilled();
				} else {
					totalLiters = lastSupplyFullTank.getLitersFilled();
				}
				return (obj.getOdometer() - lastSupplyFullTank.getOdometer()) / totalLiters;
			}
		}
		return 0.0;
	}

	public void checkSubsequentSuppliesFullTank(Supply obj) {
	}

}
