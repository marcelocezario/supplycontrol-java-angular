package br.com.satsolucoes.supplycontrol.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.satsolucoes.supplycontrol.entities.Vehicle;
import br.com.satsolucoes.supplycontrol.repositories.VehicleRepository;
import br.com.satsolucoes.supplycontrol.services.exceptions.DatabaseException;
import br.com.satsolucoes.supplycontrol.services.exceptions.ResourceNotFoundException;

@Service
public class VehicleService {
	
	@Autowired
	private VehicleRepository repository;

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
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public void updateData(Vehicle entity, Vehicle obj) {
		entity.setLicensePlate(obj.getLicensePlate());
		entity.setModelVehicle(obj.getModelVehicle());
		entity.setModelYear(obj.getModelYear());
	}
	
	public Vehicle update(Long id, Vehicle obj) {
		try {
			Vehicle entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
}
