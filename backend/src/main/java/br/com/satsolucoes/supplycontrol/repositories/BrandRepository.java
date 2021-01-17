package br.com.satsolucoes.supplycontrol.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.satsolucoes.supplycontrol.entities.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

	@Query("SELECT DISTINCT obj FROM Brand obj JOIN FETCH obj.vehicleModels")
	List<Brand> findBrandsWithVehicleModels();
}
