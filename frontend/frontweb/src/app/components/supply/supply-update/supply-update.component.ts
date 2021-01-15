import { Supply } from './../supply.model';
import { ActivatedRoute, Router } from '@angular/router';
import { SupplyService } from './../supply.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-supply-update',
  templateUrl: './supply-update.component.html',
  styleUrls: ['./supply-update.component.css']
})
export class SupplyUpdateComponent implements OnInit {

  supply: Supply

  constructor(private supplyService: SupplyService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id')
    this.supplyService.readById(id).subscribe(supply => {
      this.supply = supply;
    })
  }

  updateSupply(): void {
    this.supplyService.update(this.supply).subscribe(() => {
      this.supplyService.showMessage('Abastecimento atualizado com sucesso!');
      this.router.navigate(['/supplies']);
    })

  }

  cancel(): void {
    this.router.navigate(['/supplies'])
  }

}

