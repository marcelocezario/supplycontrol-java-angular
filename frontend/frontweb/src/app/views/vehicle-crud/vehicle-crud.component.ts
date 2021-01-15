import { HeaderService } from './../../components/template/header/header.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'

@Component({
  selector: 'app-vehicle-crud',
  templateUrl: './vehicle-crud.component.html',
  styleUrls: ['./vehicle-crud.component.css']
})
export class VehicleCrudComponent implements OnInit {

  constructor(private router: Router, private headerService: HeaderService) {
    headerService.headerData = {
      title: 'Cadastro de Veículos',
      icon: 'local_shipping',
      routeUrl: '/vehicles'
    }
  }

  ngOnInit(): void {
  }

  navigateToVehicleCreate(): void {
    this.router.navigate(['/vehicles/create'])
  }
}
