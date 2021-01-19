import { VehiclemodelService } from './../../vehiclemodel/vehiclemodel.service';
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

  vehiclemodels: VehicleModel[]

  vehicle: Vehicle

  constructor(private vehicleService: VehicleService, private router: Router,
    private route: ActivatedRoute, private brandService: BrandService, private vehiclemodelService: VehiclemodelService) { }

  ngOnInit(): void {
    this.brandService.read().subscribe(brands => {
      this.brands = brands
    })
    
    const id = this.route.snapshot.paramMap.get('id')
    this.vehicleService.readById(id).subscribe(vehicle => {
      this.vehicle = vehicle;

      this.vehiclemodelService.readByBrand(vehicle.vehicleModel.brand.id).subscribe(vehiclemodels => {
        this.vehiclemodels = vehiclemodels
      })
    })
  }

  selectedBrand(idBrand: number): void {
    this.vehiclemodelService.readByBrand(idBrand).subscribe(vehiclemodels => {
      this.vehiclemodels = vehiclemodels
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
