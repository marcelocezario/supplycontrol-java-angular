import { HeaderService } from './../../components/template/header/header.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'

@Component({
  selector: 'app-supply-crud',
  templateUrl: './supply-crud.component.html',
  styleUrls: ['./supply-crud.component.css']
})
export class SupplyCrudComponent implements OnInit {

  constructor(private router: Router, private headerService: HeaderService) {
    headerService.headerData = {
      title: 'Cadastro de Abastecimentos',
      icon: 'local_gas_station',
      routeUrl: '/supplies'
    }
  }

  ngOnInit(): void {
  }

  navigateToSupplyCreate(): void {
    this.router.navigate(['/supplies/create'])
  }
}
