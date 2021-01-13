package br.com.satsolucoes.supplycontrol.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.satsolucoes.supplycontrol.entities.ModelVehicle;
import br.com.satsolucoes.supplycontrol.repositories.ModelVehicleRepository;
import br.com.satsolucoes.supplycontrol.services.exceptions.DatabaseException;
import br.com.satsolucoes.supplycontrol.services.exceptions.ResourceNotFoundException;

@Service
public class ModelVehicleService {

	@Autowired
	private ModelVehicleRepository repository;

	public List<ModelVehicle> findAll() {
		return repository.findAll();
	}

	public ModelVehicle findById(Long id) {
		Optional<ModelVehicle> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public ModelVehicle insert(ModelVehicle obj) {
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

	public void updateData(ModelVehicle entity, ModelVehicle obj) {
		entity.setName(obj.getName());
		entity.setBrand(obj.getBrand());
	}

	public ModelVehicle update(Long id, ModelVehicle obj) {
		try {
			ModelVehicle entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
}
