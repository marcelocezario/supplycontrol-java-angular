package br.com.satsolucoes.supplycontrol.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.satsolucoes.supplycontrol.entities.Supply;
import br.com.satsolucoes.supplycontrol.entities.Vehicle;

@Repository
public interface SupplyRepository extends JpaRepository<Supply, Long> {
	
	List<Supply> findByVehicleOrderByOdometerDesc(@Param("Vehicle") Vehicle vehicle);
	
	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT sum(obj.litersFilled) FROM Supply obj WHERE obj.vehicle LIKE :vehicle AND obj.odometer > :odometerMin AND obj.odometer < :odometerMax")
	Double sumLitersFilledInTheInterval(@Param("vehicle") Vehicle vehicle, @Param("odometerMin") Integer odometerMin, @Param("odometerMax") Integer odometerMax);

}
