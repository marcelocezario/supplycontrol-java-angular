import { Brand } from './../../brand/brand.model';
import { BrandService } from './../../brand/brand.service';
import { VehiclemodelService } from './../vehiclemodel.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { VehicleModel } from '../vehiclemodel.model';
import { FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-vehiclemodel-create',
  templateUrl: './vehiclemodel-create.component.html',
  styleUrls: ['./vehiclemodel-create.component.css']
})
export class VehiclemodelCreateComponent implements OnInit {

  formControl = new FormControl ('', Validators.required);

  brands: Brand[]

  vehiclemodel: VehicleModel = {
    name: '',
    brand: null
  }

  constructor(private vehiclemodelService: VehiclemodelService,
    private router: Router, private brandService: BrandService) { }

  ngOnInit(): void {
    this.brandService.read().subscribe(brands => {
      this.brands = brands
    })
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