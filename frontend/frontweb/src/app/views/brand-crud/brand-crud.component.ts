import { HeaderService } from './../../components/template/header/header.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'

@Component({
  selector: 'app-brand-crud',
  templateUrl: './brand-crud.component.html',
  styleUrls: ['./brand-crud.component.css']
})
export class BrandCrudComponent implements OnInit {

  constructor(private router: Router, private headerService: HeaderService) {
    headerService.headerData = {
      title: 'Cadastro de Marcas',
      icon: 'local_offer',
      routeUrl: '/brands'
    }
  }

  ngOnInit(): void {
  }

  navigateToBrandCreate(): void {
    this.router.navigate(['/brands/create'])
  }
}
