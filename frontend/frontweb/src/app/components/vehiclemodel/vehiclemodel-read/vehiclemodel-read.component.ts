import { VehiclemodelService } from './../vehiclemodel.service';
import { Component, OnInit } from '@angular/core';
import { Vehiclemodel } from '../vehiclemodel.model';

@Component({
  selector: 'app-vehiclemodel-read',
  templateUrl: './vehiclemodel-read.component.html',
  styleUrls: ['./vehiclemodel-read.component.css']
})
export class VehiclemodelReadComponent implements OnInit {

  vehiclemodels: Vehiclemodel[]
  displayedColumns = ['id', 'name', 'action']

  constructor(private vehiclemodelService: VehiclemodelService) { }

  ngOnInit(): void {
    this.vehiclemodelService.read().subscribe(vehiclemodels => {
      this.vehiclemodels = vehiclemodels
      console.log(vehiclemodels)
    })
  }
}
