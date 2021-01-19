import { VehicleModelService } from './../../vehiclemodel/vehiclemodel.service';
import { VehicleModel } from './../../vehiclemodel/vehiclemodel.model';
import { BrandService } from './../../brand/brand.service';
import { Brand } from './../../brand/brand.model';
import { Vehicle } from './../vehicle.model';
import { ActivatedRoute, Router } from '@angular/router';
import { VehicleService } from './../vehicle.service';
import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';


@Component({
  selector: 'app-vehicle-update',
  templateUrl: './vehicle-update.component.html',
  styleUrls: ['./vehicle-update.component.css']
})
export class VehicleUpdateComponent implements OnInit {
  
  formControl = new FormControl ('', Validators.required);

  brands: Brand[]

  brand: Brand

  vehicleModels: VehicleModel[]

  vehicleModel: VehicleModel

  vehicle: Vehicle

  constructor(private vehicleService: VehicleService, private router: Router,
    private route: ActivatedRoute, private brandService: BrandService, private vehicleModelService: VehicleModelService) { }

  ngOnInit(): void {
    this.brandService.read().subscribe(brands => {
      this.brands = brands
    })
    
    const id = this.route.snapshot.paramMap.get('id')
    this.vehicleService.readById(id).subscribe(vehicle => {
      this.vehicle = vehicle;

      this.vehicleModelService.readByBrand(this.vehicle.vehicleModel.brand.id).subscribe(vehicleModels => {
        this.vehicleModels = vehicleModels
      })
    })
  }

  selectedBrand(idBrand: number): void {
    this.vehicleModelService.readByBrand(idBrand).subscribe(vehicleModels => {
      this.vehicleModels = vehicleModels
    })
  }

  updateVehicle(): void {
    this.vehicleService.update(this.vehicle).subscribe(() => {
      this.vehicleService.showMessage('Veículo atualizado com sucesso!');
      this.router.navigate(['/vehicles']);
    })
  }

  cancel(): void {
    this.router.navigate(['/vehicles'])
  }
}
