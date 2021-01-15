import { SupplyService } from './../supply.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Supply } from '../supply.model';

@Component({
  selector: 'app-supply-create',
  templateUrl: './supply-create.component.html',
  styleUrls: ['./supply-create.component.css']
})
export class SupplyCreateComponent implements OnInit {

  supply: Supply = {
    moment: '',
    odometer: 0,
    litersFilled: 0,
    literValueOfFuel: 0,
    fullTank: true,
    averageConsumption: 0,
    fuel: 0
  }

  constructor(private supplyService: SupplyService,
    private router: Router) { }

  ngOnInit(): void {
  }

  createSupply(): void {
    this.supplyService.create(this.supply).subscribe(() => {
      this.supplyService.showMessage('Abastecimento criado com sucesso!')
      this.router.navigate(['/supplies'])
    })
  }

  cancel(): void {
    this.router.navigate(['/supplies'])
  }

}
