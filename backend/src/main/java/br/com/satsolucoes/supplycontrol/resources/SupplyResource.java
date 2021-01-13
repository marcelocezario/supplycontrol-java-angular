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

import br.com.satsolucoes.supplycontrol.dto.SupplyDTO;
import br.com.satsolucoes.supplycontrol.entities.Supply;
import br.com.satsolucoes.supplycontrol.services.SupplyService;

@RestController
@RequestMapping(value="/supplies")
public class SupplyResource {
	
	@Autowired
	private SupplyService service;
	
	@GetMapping
	public ResponseEntity<List<SupplyDTO>> findAll() {
		List<SupplyDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Supply> findById(@PathVariable Long id) {
		Supply obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Supply> update(@PathVariable Long id, @RequestBody Supply obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
