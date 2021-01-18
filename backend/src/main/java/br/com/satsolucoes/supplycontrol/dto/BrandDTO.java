package br.com.satsolucoes.supplycontrol.dto;

import java.io.Serializable;

import br.com.satsolucoes.supplycontrol.entities.Brand;

public class BrandDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String imageUrl;
	private Integer totalTraveled;
	private Double totalLitersUsed;
	private Double overallAverage;

	public BrandDTO() {
	}
	
	public BrandDTO(Long id, String name, String imageUrl, Integer totalTraveled, Double totalLitersUsed) {
		super();
		this.id = id;
		this.name = name;
		this.imageUrl = imageUrl;
		this.totalTraveled = totalTraveled;
		this.totalLitersUsed = totalLitersUsed;
		this.overallAverage = totalTraveled / totalLitersUsed;
	}
	
	public BrandDTO(Brand entity) {
		id = entity.getId();
		name = entity.getName();
		imageUrl = entity.getImageUrl();
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Integer getTotalTraveled() {
		return totalTraveled;
	}

	public void setTotalTraveled(Integer totalTraveled) {
		this.totalTraveled = totalTraveled;
	}

	public Double getTotalLitersUsed() {
		return totalLitersUsed;
	}

	public void setTotalLitersUsed(Double totalLitersUsed) {
		this.totalLitersUsed = totalLitersUsed;
	}

	public Double getOverallAverage() {
		return overallAverage;
	}

	public void setOverallAverage(Double overallAverage) {
		this.overallAverage = overallAverage;
	}
}
