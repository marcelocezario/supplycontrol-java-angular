import { Vehicle } from './../vehicle.model';
import { ActivatedRoute, Router } from '@angular/router';
import { VehicleService } from './../vehicle.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-vehicle-update',
  templateUrl: './vehicle-update.component.html',
  styleUrls: ['./vehicle-update.component.css']
})
export class VehicleUpdateComponent implements OnInit {

  vehicle: Vehicle

  constructor(private vehicleService: VehicleService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id')
    this.vehicleService.readById(id).subscribe(vehicle => {
      this.vehicle = vehicle;
    })
  }

  updateBrand(): void {
    this.vehicleService.update(this.vehicle).subscribe(() => {
      this.vehicleService.showMessage('Veículo atualizado com sucesso!');
      this.router.navigate(['/vehicles']);
    })
  }

  cancel(): void {
    this.router.navigate(['/vehicles'])
  }
}
