import { VehiclemodelService } from './../vehiclemodel.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Vehiclemodel } from '../vehiclemodel.model';

@Component({
  selector: 'app-vehiclemodel-create',
  templateUrl: './vehiclemodel-create.component.html',
  styleUrls: ['./vehiclemodel-create.component.css']
})
export class VehiclemodelCreateComponent implements OnInit {

  vehiclemodel: Vehiclemodel = {
    name: ''
  }

  constructor(private vehiclemodelService: VehiclemodelService,
    private router: Router) { }

  ngOnInit(): void {
  }

  createVehiclemodel(): void {
    this.vehiclemodelService.create(this.vehiclemodel).subscribe(() => {
      this.vehiclemodelService.showMessage('Modelo de veículo criado com sucesso!')
      this.router.navigate(['/vehiclemodels'])
    })
  }

  cancel(): void {
    this.router.navigate(['/vehiclemodels'])
  }
}