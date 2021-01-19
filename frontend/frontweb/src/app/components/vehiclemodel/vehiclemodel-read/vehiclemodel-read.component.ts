import { VehiclemodelService } from './../vehiclemodel.service';
import { Component, OnInit } from '@angular/core';
import { VehicleModel } from '../vehiclemodel.model';

@Component({
  selector: 'app-vehiclemodel-read',
  templateUrl: './vehiclemodel-read.component.html',
  styleUrls: ['./vehiclemodel-read.component.css']
})
export class VehiclemodelReadComponent implements OnInit {

  vehiclemodels: VehicleModel[]
  displayedColumns = ['id', 'marca', 'name', 'action']

  constructor(private vehiclemodelService: VehiclemodelService) { }

  ngOnInit(): void {
    this.vehiclemodelService.read().subscribe(vehiclemodels => {
      this.vehiclemodels = vehiclemodels
      console.log(vehiclemodels)
    })
  }
}
