import { VehicleService } from './../vehicle.service';
import { Component, OnInit } from '@angular/core';
import { Vehicle } from '../vehicle.model';

@Component({
  selector: 'app-vehicle-read',
  templateUrl: './vehicle-read.component.html',
  styleUrls: ['./vehicle-read.component.css']
})
export class VehicleReadComponent implements OnInit {

  vehicles: Vehicle[]
  displayedColumns = ['id', 'licensePlate', 'modelYear', 'tankCapacity', 'action']

  constructor(private vehicleService: VehicleService) { }

  ngOnInit(): void {
    this.vehicleService.read().subscribe(vehicles => {
      this.vehicles = vehicles
      console.log(vehicles)
    })
  }

}

