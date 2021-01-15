import { VehicleService } from './../vehicle.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Vehicle } from '../vehicle.model';


@Component({
  selector: 'app-vehicle-create',
  templateUrl: './vehicle-create.component.html',
  styleUrls: ['./vehicle-create.component.css']
})
export class VehicleCreateComponent implements OnInit {

  vehicle: Vehicle = {
    licensePlate: '',
    modelYear: 0,
    tankCapacity: 0
  }

  constructor(private vehicleService: VehicleService,
    private router: Router) { }

  ngOnInit(): void {
  }

  createVehicle(): void {
    this.vehicleService.create(this.vehicle).subscribe(() => {
      this.vehicleService.showMessage('Veículo criado com sucesso!')
      this.router.navigate(['/vehicles'])
    })
  }

  cancel(): void {
    this.router.navigate(['/vehicles'])
  }

}
