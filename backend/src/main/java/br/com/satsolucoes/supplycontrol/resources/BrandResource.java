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

import br.com.satsolucoes.supplycontrol.entities.Brand;
import br.com.satsolucoes.supplycontrol.services.BrandService;

@RestController
@RequestMapping(value="/brands")
public class BrandResource {
	
	@Autowired
	private BrandService service;
	
	@GetMapping
	public ResponseEntity<List<Brand>> findAll() {
		List<Brand> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Brand> findById(@PathVariable Long id) {
		Brand obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Brand> update(@PathVariable Long id, @RequestBody Brand obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
