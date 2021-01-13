package br.com.satsolucoes.supplycontrol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.satsolucoes.supplycontrol.entities.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

}
