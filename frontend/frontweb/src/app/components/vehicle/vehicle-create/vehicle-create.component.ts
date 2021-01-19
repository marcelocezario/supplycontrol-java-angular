import { VehicleModel } from './../../vehiclemodel/vehiclemodel.model';
import { VehicleModelService } from './../../vehiclemodel/vehiclemodel.service';
import { Brand } from './../../brand/brand.model';
import { BrandService } from './../../brand/brand.service';
import { VehicleService } from './../vehicle.service';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Vehicle } from '../vehicle.model';
import { FormControl, Validators } from '@angular/forms';


@Component({
  selector: 'app-vehicle-create',
  templateUrl: './vehicle-create.component.html',
  styleUrls: ['./vehicle-create.component.css']
})
export class VehicleCreateComponent implements OnInit {

  formControl = new FormControl ('', Validators.required);

  brands: Brand[]

  brand: Brand

  vehiclemodels: VehicleModel[]

  vehicle: Vehicle = {
    licensePlate: '',
    modelYear: null,
    tankCapacity: null,
    vehicleModel: null
  }

  constructor(private vehicleService: VehicleService, private router: Router, private route: ActivatedRoute,
    private brandService: BrandService, private vehiclemodelService: VehicleModelService) { }

  ngOnInit(): void {
    this.brandService.read().subscribe(brands => {
      this.brands = brands
    })
  }

  selectedBrand(idBrand: number): void {
    this.vehiclemodelService.readByBrand(idBrand).subscribe(vehiclemodels => {
      this.vehiclemodels = vehiclemodels
    })
  }

  createVehicle(): void {
    this.vehicleService.create(this.vehicle).subscribe(() => {
      this.vehicleService.showMessage('Veículo criado com sucesso!')
      this.router.navigate(['/vehicles'])
    })
  }

  cancel(): void {
    this.router.navigate(['/vehicles'])
  }
}
