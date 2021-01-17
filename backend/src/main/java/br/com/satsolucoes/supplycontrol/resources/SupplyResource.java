package br.com.satsolucoes.supplycontrol.resources;

import java.net.URI;
import java.time.Instant;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.satsolucoes.supplycontrol.dto.SupplyDTO;
import br.com.satsolucoes.supplycontrol.dto.SupplyDTOforSave;
import br.com.satsolucoes.supplycontrol.entities.Supply;
import br.com.satsolucoes.supplycontrol.services.SupplyService;

@RestController
@RequestMapping(value = "/supplies")
public class SupplyResource {

	@Autowired
	private SupplyService service;

	@GetMapping
	public ResponseEntity<List<SupplyDTO>> findAll() {
		List<Supply> list = service.findAll();
		List<SupplyDTO> listDto = list.stream().map(x -> new SupplyDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<SupplyDTO> findById(@PathVariable Long id) {
		Supply obj = service.findById(id);
		return ResponseEntity.ok().body(new SupplyDTO(obj));
	}

	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody SupplyDTOforSave objDTO) {
		Supply obj = service.fromDTO(objDTO);
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
	public ResponseEntity<Void> update(@RequestBody SupplyDTOforSave objDTO, @PathVariable Long id) {
		Supply obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(value = "/filter")
	public ResponseEntity<List<SupplyDTO>> findByMomentBetween(@RequestParam("initialDate") Instant initialDate,
			@RequestParam("finalDate") Instant finalDate) {

		List<Supply> list = service.findByMomentBetween(initialDate, finalDate);
		List<SupplyDTO> listDto = list.stream().map(x -> new SupplyDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
}
