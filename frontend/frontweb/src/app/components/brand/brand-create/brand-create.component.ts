import { BrandService } from './../brand.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Brand } from '../brand.model';

@Component({
  selector: 'app-brand-create',
  templateUrl: './brand-create.component.html',
  styleUrls: ['./brand-create.component.css']
})
export class BrandCreateComponent implements OnInit {

  brand: Brand = {
    name: '',
    imageUrl: ''
  }

  constructor(private brandService: BrandService,
    private router: Router) { }

  ngOnInit(): void {
  }

  createBrand(): void {
    this.brandService.create(this.brand).subscribe(() => {
      this.brandService.showMessage('Marca criada com sucesso!')
      this.router.navigate(['/brands'])
    })
  }

  cancel(): void {
    this.router.navigate(['/brands'])
  }

}
