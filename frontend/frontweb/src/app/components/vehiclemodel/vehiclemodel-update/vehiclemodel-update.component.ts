import { Vehiclemodel } from './../vehiclemodel.model';
import { ActivatedRoute, Router } from '@angular/router';
import { VehiclemodelService } from './../vehiclemodel.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-vehiclemodel-update',
  templateUrl: './vehiclemodel-update.component.html',
  styleUrls: ['./vehiclemodel-update.component.css']
})
export class VehiclemodelUpdateComponent implements OnInit {

  vehiclemodel: Vehiclemodel

  constructor(private vehiclemodelService: VehiclemodelService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
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