package br.com.satsolucoes.supplycontrol.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.satsolucoes.supplycontrol.entities.Brand;

public class BrandDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	
	public BrandDTO() {
	}

	public BrandDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public BrandDTO(Brand entity) {
		id = entity.getId();
		name = entity.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
