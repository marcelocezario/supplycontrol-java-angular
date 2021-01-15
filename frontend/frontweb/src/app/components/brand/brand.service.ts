import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';
import { Brand } from './brand.model';

@Injectable({
  providedIn: 'root'
})
export class BrandService {

  baseUrl = "http://localhost:3001/brands"

  constructor(private snackBar: MatSnackBar, private http: HttpClient) { }

  showMessage(msg: string): void {
    this.snackBar.open(msg, 'X', {
      duration: 3000,
      horizontalPosition: "right",
      verticalPosition: "top"
    })
  }

  create(brand: Brand): Observable<Brand> {
    return this.http.post<Brand>(this.baseUrl, brand)
  }

  read(): Observable<Brand[]> {
    return this.http.get<Brand[]>(this.baseUrl)
  }
}
