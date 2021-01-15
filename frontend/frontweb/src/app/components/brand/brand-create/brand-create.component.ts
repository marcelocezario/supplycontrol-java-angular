import { BrandService } from './../brand.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-brand-create',
  templateUrl: './brand-create.component.html',
  styleUrls: ['./brand-create.component.css']
})
export class BrandCreateComponent implements OnInit {

  constructor(private brandService: BrandService,
    private router: Router) { }

  ngOnInit(): void {
  }

  createBrand(): void {
    this.brandService.showMessage('Marca criada com sucesso!')
  }

  cancel(): void {
    this.router.navigate(['/brands'])
  }

}
