package br.com.satsolucoes.supplycontrol.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.satsolucoes.supplycontrol.entities.Brand;
import br.com.satsolucoes.supplycontrol.entities.VehicleModel;

@Repository
public interface VehicleModelRepository extends JpaRepository<VehicleModel, Long> {

	@Query("SELECT DISTINCT obj FROM VehicleModel obj JOIN FETCH obj.vehicles")
	List<VehicleModel> findVehicleModelsWithVehicles();

	List<VehicleModel> findByBrand(Brand brand);

}
