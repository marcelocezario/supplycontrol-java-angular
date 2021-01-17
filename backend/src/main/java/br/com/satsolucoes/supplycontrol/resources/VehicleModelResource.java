package br.com.satsolucoes.supplycontrol.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.satsolucoes.supplycontrol.dto.VehicleModelDTO;
import br.com.satsolucoes.supplycontrol.dto.VehicleModelDTOforSave;
import br.com.satsolucoes.supplycontrol.entities.VehicleModel;
import br.com.satsolucoes.supplycontrol.services.VehicleModelService;

@RestController
@RequestMapping(value = "/vehiclemodels")
public class VehicleModelResource {

	@Autowired
	private VehicleModelService service;

	@GetMapping
	public ResponseEntity<List<VehicleModelDTO>> findAll() {
		List<VehicleModel> list = service.findAll();
		List<VehicleModelDTO> listDto = list.stream().map(x -> new VehicleModelDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<VehicleModelDTO> findById(@PathVariable Long id) {
		VehicleModel obj = service.findById(id);
		return ResponseEntity.ok().body(new VehicleModelDTO(obj));
	}

	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody VehicleModelDTOforSave objDTO) {
		VehicleModel obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@RequestBody VehicleModelDTOforSave objDTO, @PathVariable Long id) {
		VehicleModel obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
}
