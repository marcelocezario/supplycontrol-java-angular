package br.com.satsolucoes.supplycontrol.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.satsolucoes.supplycontrol.entities.Brand;
import br.com.satsolucoes.supplycontrol.entities.VehicleModel;
import br.com.satsolucoes.supplycontrol.entities.Supply;
import br.com.satsolucoes.supplycontrol.entities.Vehicle;
import br.com.satsolucoes.supplycontrol.entities.enums.Fuel;
import br.com.satsolucoes.supplycontrol.repositories.BrandRepository;
import br.com.satsolucoes.supplycontrol.repositories.VehicleModelRepository;
import br.com.satsolucoes.supplycontrol.repositories.SupplyRepository;
import br.com.satsolucoes.supplycontrol.repositories.VehicleRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private BrandRepository bRepository;
	
	@Autowired
	private VehicleModelRepository mvRepository;
	
	@Autowired
	private VehicleRepository vRepository;

	@Autowired
	private SupplyRepository sRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Brand b1 = new Brand(null, "Scania");
		Brand b2 = new Brand(null, "Mercedes");
		Brand b3 = new Brand(null, "Volvo");
		Brand b4 = new Brand(null, "Volkswagen");
		
		VehicleModel vm1 = new VehicleModel(null, "R114", b1);
		VehicleModel vm2 = new VehicleModel(null, "P310", b1);
		VehicleModel vm3 = new VehicleModel(null, "LS 1634", b2);
		VehicleModel vm4 = new VehicleModel(null, "25390", b4);
		VehicleModel vm5 = new VehicleModel(null, "FH12", b3);
		
		Vehicle v1 = new Vehicle(null, "AAA1A111", 2020, vm1);
		Vehicle v2 = new Vehicle(null, "BBB1A111", 2019, vm1);
		Vehicle v3 = new Vehicle(null, "CCC1A111", 2018, vm3);
		Vehicle v4 = new Vehicle(null, "DDD1A111", 2000, vm2);
		Vehicle v5 = new Vehicle(null, "EEE1A111", 1999, vm5);
		Vehicle v6 = new Vehicle(null, "FFF1A111", 1998, vm3);
		Vehicle v7 = new Vehicle(null, "GGG1A111", 2020, vm3);
		
		
		Supply s1 = new Supply(null, Instant.parse("2020-01-01T00:00:00Z"), 500, 60.0, true, Fuel.DIESEL_S10, v1);
		Supply s2 = new Supply(null, Instant.parse("2020-01-02T00:00:00Z"), 1000, 90.0, false, Fuel.DIESEL_S10, v1);
		Supply s3 = new Supply(null, Instant.parse("2020-01-03T00:00:00Z"), 1300, 95.0, true, Fuel.DIESEL_S10, v1);
		Supply s4 = new Supply(null, Instant.parse("2020-01-04T00:00:00Z"), 2000, 110.0, true, Fuel.DIESEL_S10, v1);
		Supply s5 = new Supply(null, Instant.parse("2020-01-05T00:00:00Z"), 1000, 80.0, true, Fuel.DIESEL_S10, v2);
		Supply s6 = new Supply(null, Instant.parse("2020-01-06T00:00:00Z"), 1500, 130.0, false, Fuel.DIESEL_S10, v2);
		
		bRepository.saveAll(Arrays.asList(b1, b2, b3, b4));
		mvRepository.saveAll(Arrays.asList(vm1, vm2, vm3, vm4, vm5));
		vRepository.saveAll(Arrays.asList(v1, v2, v3, v4, v5, v6, v7));
		sRepository.saveAll(Arrays.asList(s1, s2, s3, s4, s5, s6));
		
	}

}