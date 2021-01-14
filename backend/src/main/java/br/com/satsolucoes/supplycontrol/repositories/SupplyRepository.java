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

	@Transactional(readOnly=true)
	@Query("SELECT max(obj.odometer) FROM Supply obj WHERE obj.fullTank LIKE true AND obj.vehicle LIKE :vehicle AND obj.odometer < :odometer")
	Supply findLastSupplyWithFullTank(@Param("vehicle") Vehicle vehicle, @Param("odometer") Integer odometer);

	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT min(obj) FROM Supply obj LEFT JOIN obj.vehicle vehicle  WHERE obj.fullTank LIKE true AND obj.vehicle LIKE :vehicle AND obj.odometer > :odometer")
	Supply findNextSupplyWithFullTank(@Param("vehicle") Vehicle vehicle, @Param("odometer") Integer odometer);

	
	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Supply obj WHERE obj.vehicle LIKE :vehicle AND obj.odometer > :odometerMin AND obj.odometer < :odometerMax")
	List<Supply> findSupplyInterval(@Param("vehicle") Vehicle vehicle, @Param("odometerMin") Integer odometerMin, @Param("odometerMax") Integer odometerMax);

	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT sum(obj.litersFilled) FROM Supply obj WHERE obj.vehicle LIKE :vehicle AND obj.odometer > :odometerMin AND obj.odometer < :odometerMax")
	Double sumLitersFilledInTheInterval(@Param("vehicle") Vehicle vehicle, @Param("odometerMin") Integer odometerMin, @Param("odometerMax") Integer odometerMax);

	
	
	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Supply obj WHERE obj.fullTank LIKE true AND obj.vehicle LIKE :vehicle AND obj.odometer < :odometer ORDER BY obj.odometer DESC")
	List<Supply> teste(@Param("vehicle") Vehicle vehicle, @Param("odometer") Integer odometer);

	
	
}
