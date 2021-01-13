package br.com.satsolucoes.supplycontrol.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.satsolucoes.supplycontrol.entities.ModelVehicle;
import br.com.satsolucoes.supplycontrol.services.ModelVehicleService;

@RestController
@RequestMapping(value="/vehiclemodels")
public class ModelVehicleResource {
	
	@Autowired
	private ModelVehicleService service;
	
	@GetMapping
	public ResponseEntity<List<ModelVehicle>> findAll() {
		List<ModelVehicle> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ModelVehicle> findById(@PathVariable Long id) {
		ModelVehicle obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ModelVehicle> update(@PathVariable Long id, @RequestBody ModelVehicle obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
