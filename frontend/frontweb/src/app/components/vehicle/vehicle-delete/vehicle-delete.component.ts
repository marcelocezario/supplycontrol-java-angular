import { Router, ActivatedRoute } from '@angular/router';
import { VehicleService } from './../vehicle.service';
import { Vehicle } from './../vehicle.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-vehicle-delete',
  templateUrl: './vehicle-delete.component.html',
  styleUrls: ['./vehicle-delete.component.css']
})
export class VehicleDeleteComponent implements OnInit {

  vehicle: Vehicle

  constructor(private vehicleService: VehicleService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    this.vehicleService.readById(id).subscribe((vehicle) => {
      this.vehicle = vehicle;
    })
  }

  deleteVehicle(): void {
    this.vehicleService.delete(this.vehicle.id).subscribe(() => {
      this.vehicleService.showMessage("Veículo excluído com sucesso!", true) 
      this.router.navigate(["/vehicles"]);
    })
  }

  cancel(): void {
    this.router.navigate(['/vehicles'])
  }

}
