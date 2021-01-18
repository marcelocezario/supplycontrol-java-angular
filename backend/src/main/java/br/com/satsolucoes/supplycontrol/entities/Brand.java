package br.com.satsolucoes.supplycontrol.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.satsolucoes.supplycontrol.services.BrandService;

@Entity
@Table(name = "tb_brand")
public class Brand implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String imageUrl;

	@OneToMany(mappedBy = "brand")
	private Set<VehicleModel> vehicleModels = new HashSet<>();

	public Brand() {
	}

	public Brand(Long id, String name, String imageUrl) {
		super();
		this.id = id;
		this.name = name;
		this.imageUrl = imageUrl;
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

//	public Double getOverallAverage() {
//		Integer totalTraveled = getTotalTraveled();
//		Double totalLiters = getTotalLitersUsed();
//		if (totalTraveled > 0 && totalLiters > 0) {
//			return totalTraveled / totalLiters;
//		}
//		return 0.0;
//	}

	public Set<VehicleModel> getVehicleModels() {
		return vehicleModels;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Brand other = (Brand) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
