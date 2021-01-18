import { Brand } from './../brand.model';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-brand-card',
  templateUrl: './brand-card.component.html',
  styleUrls: ['./brand-card.component.css']
})
export class BrandCardComponent implements OnInit {

  @Input() brand: Brand;

  constructor() { }

  ngOnInit(): void {
  }

}
