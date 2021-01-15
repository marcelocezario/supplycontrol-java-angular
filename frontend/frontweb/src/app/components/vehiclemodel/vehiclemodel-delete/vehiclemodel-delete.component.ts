import { Router, ActivatedRoute } from '@angular/router';
import { VehiclemodelService } from './../vehiclemodel.service';
import { Vehiclemodel } from './../vehiclemodel.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-vehiclemodel-delete',
  templateUrl: './vehiclemodel-delete.component.html',
  styleUrls: ['./vehiclemodel-delete.component.css']
})
export class VehiclemodelDeleteComponent implements OnInit {

  vehiclemodel: Vehiclemodel

  constructor(private vehiclemodelService: VehiclemodelService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    this.vehiclemodelService.readById(id).subscribe((vehiclemodel) => {
      this.vehiclemodel = vehiclemodel;
    })
  }

  deleteVehiclemodel(): void {
    this.vehiclemodelService.delete(this.vehiclemodel.id).subscribe(() => {
      this.vehiclemodelService.showMessage("Modelo de veículo excluído com sucesso!", true) 
      this.router.navigate(["/vehiclemodels"]);
    })
  }

  cancel(): void {
    this.router.navigate(['/vehiclemodels'])
  }

}
