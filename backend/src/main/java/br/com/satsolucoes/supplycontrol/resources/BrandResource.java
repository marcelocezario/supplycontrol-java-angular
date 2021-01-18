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

import br.com.satsolucoes.supplycontrol.dto.BrandDTO;
import br.com.satsolucoes.supplycontrol.entities.Brand;
import br.com.satsolucoes.supplycontrol.services.BrandService;

@RestController
@RequestMapping(value = "/brands")
public class BrandResource {

	@Autowired
	private BrandService service;

	@GetMapping
	public ResponseEntity<List<BrandDTO>> findAll() {
		List<Brand> list = service.findAll();
		List<BrandDTO> listDto = list.stream().map(x -> service.toDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<BrandDTO> findById(@PathVariable Long id) {
		Brand obj = service.findById(id);
		return ResponseEntity.ok().body(service.toDTO(obj));
	}

	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody BrandDTO objDTO) {
		Brand obj = service.fromDTO(objDTO);
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
	public ResponseEntity<Void> update(@RequestBody BrandDTO objDTO, @PathVariable Long id) {
		Brand obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
}
