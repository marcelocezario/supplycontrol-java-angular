package br.com.satsolucoes.supplycontrol.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.satsolucoes.supplycontrol.dto.VehicleDTO;
import br.com.satsolucoes.supplycontrol.entities.Vehicle;
import br.com.satsolucoes.supplycontrol.repositories.VehicleRepository;
import br.com.satsolucoes.supplycontrol.services.exceptions.ResourceNotFoundException;

@Service
public class VehicleService {

	@Autowired
	private VehicleRepository repository;

	@Autowired
	private VehicleModelService vehicleModelService;

	@Transactional(readOnly = true)
	public List<Vehicle> findAll() {
		return repository.findAll();
	}

	@Transactional(readOnly = true)
	public Vehicle findById(Long id) {
		Optional<Vehicle> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Vehicle insert(Vehicle obj) {
		return repository.save(obj);
	}

	public void delete(Long id) {
		findById(id);
		repository.deleteById(id);
	}

	public Vehicle update(Vehicle obj) {
		Vehicle newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	public void updateData(Vehicle newObj, Vehicle obj) {
		newObj.setLicensePlate(obj.getLicensePlate());
		newObj.setModelYear(obj.getModelYear());
		newObj.setTankCapacity(obj.getTankCapacity());
		newObj.setVehicleModel(obj.getVehicleModel());
	}

	public Vehicle fromDTO(VehicleDTO objDTO) {
		return new Vehicle(objDTO.getId(), objDTO.getLicensePlate(), objDTO.getModelYear(), objDTO.getTankCapacity(),
				vehicleModelService.fromDTO(objDTO.getVehicleModel()));
	}
}
