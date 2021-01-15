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
    odometer: null,
    litersFilled: null,
    literValueOfFuel: null,
    fullTank: true,
    averageConsumption: null,
    fuel: null
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
