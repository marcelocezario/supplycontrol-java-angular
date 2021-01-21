import { VehicleService } from './../../vehicle/vehicle.service';
import { Fuel } from './../fuel-enum.model';
import { Vehicle } from './../../vehicle/vehicle.model';
import { SupplyService } from './../supply.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Supply } from '../supply.model';
import { FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-supply-create',
  templateUrl: './supply-create.component.html',
  styleUrls: ['./supply-create.component.css']
})
export class SupplyCreateComponent implements OnInit {

  formControl = new FormControl ('', Validators.required);

  vehicles: Vehicle[]

  fuelEnum: Fuel;
  fuels = [];


  supply: Supply = {
    moment: null,
    odometer: null,
    litersFilled: null,
    priceTotal: null,
    fullTank: true,
    fuel: null,
    vehicle: null
  }

  constructor(private supplyService: SupplyService, private router: Router, private vehicleService: VehicleService) { }

  ngOnInit(): void {

    this.vehicleService.read().subscribe(vehicles => {
      this.vehicles = vehicles
    })

    this.fuels = Object.keys(Fuel).map(key => ({
      id: Fuel[key], name: key
    }))


  }

  createSupply(): void {
    this.supplyService.create(this.supply).subscribe(() => {
      this.supplyService.showMessage('Abastecimento criado com sucesso!')
      this.router.navigate(['/supplies'])
    })
  }

  cancel(): void {
    this.router.navigate(['/supplies'])
  }

}
