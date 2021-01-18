import { BrandService } from './../../brand/brand.service';
import { Brand } from './../../brand/brand.model';
import { Vehiclemodel } from './../vehiclemodel.model';
import { ActivatedRoute, Router } from '@angular/router';
import { VehiclemodelService } from './../vehiclemodel.service';
import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-vehiclemodel-update',
  templateUrl: './vehiclemodel-update.component.html',
  styleUrls: ['./vehiclemodel-update.component.css']
})
export class VehiclemodelUpdateComponent implements OnInit {

  brandControl = new FormControl ('', Validators.required);

  brands: Brand[]

  vehiclemodel: Vehiclemodel

  constructor(private vehiclemodelService: VehiclemodelService, private router: Router, private route: ActivatedRoute, private brandService: BrandService) { }

  ngOnInit(): void {
    this.brandService.read().subscribe(brands => {
      this.brands = brands
    })
    
    const id = this.route.snapshot.paramMap.get('id')
    this.vehiclemodelService.readById(id).subscribe(vehiclemodel => {
      this.vehiclemodel = vehiclemodel;
    })

  }

  updateVehiclemodel(): void {
    this.vehiclemodelService.update(this.vehiclemodel).subscribe(() => {
      this.vehiclemodelService.showMessage('Modelo de veículo atualizado com sucesso!');
      this.router.navigate(['/vehiclemodels']);
    })

  }

  cancel(): void {
    this.router.navigate(['/vehiclemodels'])
  }
}