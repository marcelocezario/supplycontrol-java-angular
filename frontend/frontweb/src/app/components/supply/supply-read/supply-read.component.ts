import { SupplyService } from './../supply.service';
import { Component, OnInit } from '@angular/core';
import { Supply } from '../supply.model';

@Component({
  selector: 'app-supply-read',
  templateUrl: './supply-read.component.html',
  styleUrls: ['./supply-read.component.css']
})
export class SupplyReadComponent implements OnInit {

  supplies: Supply[]
  displayedColumns = ['id', 'licensePlate', 'odometer', 'litersFilled', 'literValueOfFuel', 'fullTank', 'averageConsumption', 'action']

  constructor(private supplyService: SupplyService) { }

  ngOnInit(): void {
    this.supplyService.read().subscribe(supplies => {
      this.supplies = supplies
      console.log(supplies)
    })
  }

}
