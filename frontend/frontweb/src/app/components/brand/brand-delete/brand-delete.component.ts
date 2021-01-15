import { Router, ActivatedRoute } from '@angular/router';
import { BrandService } from './../brand.service';
import { Brand } from './../brand.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-brand-delete',
  templateUrl: './brand-delete.component.html',
  styleUrls: ['./brand-delete.component.css']
})
export class BrandDeleteComponent implements OnInit {

  brand: Brand

  constructor(private brandService: BrandService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    this.brandService.readById(id).subscribe((brand) => {
      this.brand = brand;
    })
  }

  deleteBrand(): void {
    this.brandService.delete(this.brand.id).subscribe(() => {
      this.brandService.showMessage("Marca excluída com sucesso!")
      this.router.navigate(["/brands"]);
    })
  }

  cancel(): void {
    this.router.navigate(['/brands'])
  }

}
