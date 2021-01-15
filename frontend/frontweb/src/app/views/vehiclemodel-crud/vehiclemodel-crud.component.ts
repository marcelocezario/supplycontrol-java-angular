import { HeaderService } from './../../components/template/header/header.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'

@Component({
  selector: 'app-vehiclemodel-crud',
  templateUrl: './vehiclemodel-crud.component.html',
  styleUrls: ['./vehiclemodel-crud.component.css']
})
export class VehiclemodelCrudComponent implements OnInit {

  constructor(private router: Router, private headerService: HeaderService) {
    headerService.headerData = {
      title: 'Cadastro de Modelos de Veículos',
      icon: 'toc',
      routeUrl: '/vehicleModels'
    }
  }

  ngOnInit(): void {
  }

  navigateToVehiclemodelCreate(): void {
    this.router.navigate(['/vehiclemodels/create'])
  }
}
