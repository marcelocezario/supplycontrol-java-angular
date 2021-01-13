package br.com.satsolucoes.supplycontrol.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.satsolucoes.supplycontrol.entities.VehicleModel;
import br.com.satsolucoes.supplycontrol.repositories.VehicleModelRepository;
import br.com.satsolucoes.supplycontrol.services.exceptions.DatabaseException;
import br.com.satsolucoes.supplycontrol.services.exceptions.ResourceNotFoundException;

@Service
public class VehicleModelService {

	@Autowired
	private VehicleModelRepository repository;

	@Transactional(readOnly = true)
	public List<VehicleModel> findAll() {
		return repository.findAll();
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
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public void updateData(VehicleModel entity, VehicleModel obj) {
		entity.setName(obj.getName());
		entity.setBrand(obj.getBrand());
	}

	public VehicleModel update(Long id, VehicleModel obj) {
		try {
			VehicleModel entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
}
