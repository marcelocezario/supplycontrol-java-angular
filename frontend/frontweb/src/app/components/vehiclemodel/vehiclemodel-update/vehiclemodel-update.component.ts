import { BrandService } from './../../brand/brand.service';
import { Brand } from './../../brand/brand.model';
import { VehicleModel } from './../vehiclemodel.model';
import { ActivatedRoute, Router } from '@angular/router';
import { VehicleModelService } from './../vehiclemodel.service';
import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-vehiclemodel-update',
  templateUrl: './vehiclemodel-update.component.html',
  styleUrls: ['./vehiclemodel-update.component.css']
})
export class VehiclemodelUpdateComponent implements OnInit {

  formControl = new FormControl ('', Validators.required);

  brands: Brand[]

  vehiclemodel: VehicleModel

  constructor(private vehicleModelService: VehicleModelService, private router: Router,
    private route: ActivatedRoute, private brandService: BrandService) { }

  ngOnInit(): void {
    this.brandService.read().subscribe(brands => {
      this.brands = brands
    })
    
    const id = this.route.snapshot.paramMap.get('id')
    this.vehicleModelService.readById(id).subscribe(vehiclemodel => {
      this.vehiclemodel = vehiclemodel;
    })

  }

  updateVehiclemodel(): void {
    this.vehicleModelService.update(this.vehiclemodel).subscribe(() => {
      this.vehicleModelService.showMessage('Modelo de veículo atualizado com sucesso!');
      this.router.navigate(['/vehiclemodels']);
    })

  }

  cancel(): void {
    this.router.navigate(['/vehiclemodels'])
  }
}