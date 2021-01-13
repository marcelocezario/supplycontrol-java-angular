package br.com.satsolucoes.supplycontrol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.satsolucoes.supplycontrol.entities.Supply;

@Repository
public interface SupplyRepository extends JpaRepository<Supply, Long> {

}
