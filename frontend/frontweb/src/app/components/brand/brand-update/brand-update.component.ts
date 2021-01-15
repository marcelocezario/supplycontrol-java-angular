import { Brand } from './../brand.model';
import { ActivatedRoute, Router } from '@angular/router';
import { BrandService } from './../brand.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-brand-update',
  templateUrl: './brand-update.component.html',
  styleUrls: ['./brand-update.component.css']
})
export class BrandUpdateComponent implements OnInit {

  brand: Brand

  constructor(private brandService: BrandService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id')
    this.brandService.readById(id).subscribe(brand => {
      this.brand = brand;
    })
  }

  updateBrand(): void {
    this.brandService.update(this.brand).subscribe(() => {
      this.brandService.showMessage('Marca atualizada com sucesso!');
      this.router.navigate(['/brands']);
    })

  }

  cancel(): void {
    this.router.navigate(['/brands'])
  }

}
