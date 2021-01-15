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

  supply: Supply

  constructor(private supplyService: SupplyService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    this.supplyService.readById(id).subscribe((supply) => {
      this.supply = supply;
    })
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
