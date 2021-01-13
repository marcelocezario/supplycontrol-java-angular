package br.com.satsolucoes.supplycontrol.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.satsolucoes.supplycontrol.entities.Brand;
import br.com.satsolucoes.supplycontrol.repositories.BrandRepository;
import br.com.satsolucoes.supplycontrol.services.exceptions.DatabaseException;
import br.com.satsolucoes.supplycontrol.services.exceptions.ResourceNotFoundException;

@Service
public class BrandService {
	
	@Autowired
	private BrandRepository repository;
	
	@Transactional(readOnly = true)
	public List<Brand> findAll() {
		return repository.findAll();
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
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public void updateData(Brand entity, Brand obj) {
		entity.setName(obj.getName());
	}
	
	public Brand update(Long id, Brand obj) {
		try {
			Brand entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
}
