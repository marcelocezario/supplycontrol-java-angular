import { Router, ActivatedRoute } from '@angular/router';
import { VehicleModelService } from './../vehiclemodel.service';
import { VehicleModel } from './../vehiclemodel.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-vehiclemodel-delete',
  templateUrl: './vehiclemodel-delete.component.html',
  styleUrls: ['./vehiclemodel-delete.component.css']
})
export class VehiclemodelDeleteComponent implements OnInit {

  vehiclemodel: VehicleModel

  constructor(private vehicleModelService: VehicleModelService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    this.vehicleModelService.readById(id).subscribe((vehiclemodel) => {
      this.vehiclemodel = vehiclemodel;
    })
  }

  deleteVehiclemodel(): void {
    this.vehicleModelService.delete(this.vehiclemodel.id).subscribe(() => {
      this.vehicleModelService.showMessage("Modelo de veículo excluído com sucesso!", true) 
      this.router.navigate(["/vehiclemodels"]);
    })
  }

  cancel(): void {
    this.router.navigate(['/vehiclemodels'])
  }

}
