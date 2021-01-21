import { Fuel } from './../fuel-enum.model';
import { Vehicle } from './../../vehicle/vehicle.model';
import { Router, ActivatedRoute } from '@angular/router';
import { SupplyService } from './../supply.service';
import { Supply } from './../supply.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-supply-delete',
  templateUrl: './supply-delete.component.html',
  styleUrls: ['./supply-delete.component.css']
})
export class SupplyDeleteComponent implements OnInit {


  vehicles: Vehicle[]

  fuelEnum: Fuel;
  fuels = [];

  supply: Supply


  constructor(private supplyService: SupplyService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    this.supplyService.readById(id).subscribe((supply) => {
      this.supply = supply;
    })

    this.fuels = Object.keys(Fuel).map(key => ({
      id: Fuel[key], name: key
    }))
  }

  deleteSupply(): void {
    this.supplyService.delete(this.supply.id).subscribe(() => {
      this.supplyService.showMessage("Abastecimento excluído com sucesso!", true) 
      this.router.navigate(["/supplies"]);
    })
  }

  cancel(): void {
    this.router.navigate(['/supplies'])
  }
}
