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

import br.com.satsolucoes.supplycontrol.dto.VehicleModelDTO;
import br.com.satsolucoes.supplycontrol.entities.VehicleModel;
import br.com.satsolucoes.supplycontrol.services.VehicleModelService;

@RestController
@RequestMapping(value="/vehiclemodels")
public class VehicleModelResource {
	
	@Autowired
	private VehicleModelService service;
	
	@GetMapping
	public ResponseEntity<List<VehicleModelDTO>> findAll() {
		List<VehicleModelDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<VehicleModelDTO> findById(@PathVariable Long id) {
		VehicleModel obj = service.findById(id);
		return ResponseEntity.ok().body(new VehicleModelDTO(obj));
	}
	
	@PostMapping
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<VehicleModel> update(@PathVariable Long id, @RequestBody VehicleModel obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
