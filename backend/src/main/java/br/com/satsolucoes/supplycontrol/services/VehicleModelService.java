package br.com.satsolucoes.supplycontrol.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.satsolucoes.supplycontrol.dto.VehicleModelDTO;
import br.com.satsolucoes.supplycontrol.dto.VehicleModelDTOforSave;
import br.com.satsolucoes.supplycontrol.entities.VehicleModel;
import br.com.satsolucoes.supplycontrol.repositories.VehicleModelRepository;
import br.com.satsolucoes.supplycontrol.services.exceptions.ResourceNotFoundException;

@Service
public class VehicleModelService {

	@Autowired
	private VehicleModelRepository repository;
	
	@Autowired
	private BrandService brandService;

	@Transactional(readOnly = true)
	public List<VehicleModel> findAll() {
		return repository.findVehicleModelsWithVehicles();
	}

	@Transactional(readOnly = true)
	public VehicleModel findById(Long id) {
		Optional<VehicleModel> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public VehicleModel insert(VehicleModel obj) {
		return repository.save(obj);
	}

	public void delete(Long id) {
		findById(id);
		repository.deleteById(id);
	}

	public VehicleModel update(VehicleModel obj) {
		VehicleModel newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}
	
	public void updateData(VehicleModel newObj, VehicleModel obj) {
		newObj.setName(obj.getName());
		newObj.setBrand(obj.getBrand());
	}
	
	public VehicleModel fromDTO(VehicleModelDTO objDTO) {
		return new VehicleModel(objDTO.getId(), objDTO.getName(), brandService.fromDTO(objDTO.getBrand()));
	}
	
	public VehicleModel fromDTO(VehicleModelDTOforSave objDTO) {
		return new VehicleModel(objDTO.getId(), objDTO.getName(), brandService.fromDTO(objDTO.getBrand()));
	}
}
