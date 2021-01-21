import { Fuel } from '../fuel-enum.model';
import { Vehicle } from './../../vehicle/vehicle.model';
import { VehicleService } from './../../vehicle/vehicle.service';
import { Supply } from './../supply.model';
import { ActivatedRoute, Router } from '@angular/router';
import { SupplyService } from './../supply.service';
import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-supply-update',
  templateUrl: './supply-update.component.html',
  styleUrls: ['./supply-update.component.css']
})
export class SupplyUpdateComponent implements OnInit {

  formControl = new FormControl ('', Validators.required);

  vehicles: Vehicle[]

  fuelEnum: Fuel;
  fuels = [];

  supply: Supply

  constructor(private supplyService: SupplyService, private router: Router, private route: ActivatedRoute,
    private vehicleService: VehicleService) { }



  ngOnInit(): void {

    this.vehicleService.read().subscribe(vehicles => {
      this.vehicles = vehicles
    })

    const id = this.route.snapshot.paramMap.get('id')
    this.supplyService.readById(id).subscribe(supply => {
      this.supply = supply;
    })

    this.fuels = Object.keys(Fuel).map(key => ({
      id: Fuel[key], name: key
    }))
  }

  updateSupply(): void {
    this.supplyService.update(this.supply).subscribe(() => {
      this.supplyService.showMessage('Abastecimento atualizado com sucesso!');
      this.router.navigate(['/supplies']);
    })

  }

  cancel(): void {
    this.router.navigate(['/supplies'])
  }

}

