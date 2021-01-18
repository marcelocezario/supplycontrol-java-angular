package br.com.satsolucoes.supplycontrol.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.satsolucoes.supplycontrol.dto.BrandDTO;
import br.com.satsolucoes.supplycontrol.entities.Brand;
import br.com.satsolucoes.supplycontrol.repositories.BrandRepository;
import br.com.satsolucoes.supplycontrol.services.exceptions.ResourceNotFoundException;

@Service
public class BrandService {

	@Autowired
	private BrandRepository repository;

	@Autowired
	private SupplyService supplyService;

	@Transactional(readOnly = true)
	public List<Brand> findAll() {
		return repository.findBrandsWithVehicleModels();
	}

	@Transactional(readOnly = true)
	public Brand findById(Long id) {
		Optional<Brand> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Brand insert(Brand obj) {
		return repository.save(obj);
	}

	public void delete(Long id) {
		findById(id);
		repository.deleteById(id);
	}

	public Brand update(Brand obj) {
		Brand newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	public void updateData(Brand newObj, Brand obj) {
		newObj.setName(obj.getName());
		newObj.setImageUrl(obj.getImageUrl());
	}

	public Integer getTotalTravelledDistance(Long id) {
		return supplyService.sumTravelledDistanceByBrand(findById(id));
	}

	public Double getTotalLiterUsed(Long id) {
		return supplyService.sumLitersUsedByBrand(findById(id));
	}

	public BrandDTO toDTO(Brand obj) {
		return new BrandDTO(obj.getId(), obj.getName(), obj.getImageUrl(), this.getTotalTravelledDistance(obj.getId()),
				this.getTotalLiterUsed(obj.getId()));
	}

	public Brand fromDTO(BrandDTO objDTO) {
		return new Brand(objDTO.getId(), objDTO.getName(), objDTO.getImageUrl());
	}
}
