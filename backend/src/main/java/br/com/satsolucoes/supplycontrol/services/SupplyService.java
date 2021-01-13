package br.com.satsolucoes.supplycontrol.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.satsolucoes.supplycontrol.dto.SupplyDTO;
import br.com.satsolucoes.supplycontrol.entities.Supply;
import br.com.satsolucoes.supplycontrol.repositories.SupplyRepository;
import br.com.satsolucoes.supplycontrol.services.exceptions.DatabaseException;
import br.com.satsolucoes.supplycontrol.services.exceptions.ResourceNotFoundException;

@Service
public class SupplyService {
	
	@Autowired
	private SupplyRepository repository;

	@Transactional(readOnly = true)
	public List<SupplyDTO> findAll() {
		List<Supply> list = repository.findAll();
		return list.stream().map(x -> new SupplyDTO(x)).collect(Collectors.toList());
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
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public void updateData(Supply entity, Supply obj) {
		entity.setFuel(obj.getFuel());
		entity.setFullTank(obj.isFullTank());
		entity.setLiterValueOfFuel(obj.getLiterValueOfFuel());
		entity.setMoment(obj.getMoment());
		entity.setOdometer(obj.getOdometer());
		entity.setVehicle(obj.getVehicle());
	}
	
	public Supply update(Long id, Supply obj) {
		try {
			Supply entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
}
