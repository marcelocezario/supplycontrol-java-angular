package br.com.satsolucoes.supplycontrol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.satsolucoes.supplycontrol.entities.ModelVehicle;

@Repository
public interface ModelVehicleRepository extends JpaRepository<ModelVehicle, Long> {

}
