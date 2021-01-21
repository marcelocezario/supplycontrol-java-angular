import { catchError, map } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { EMPTY, Observable } from 'rxjs';
import { Brand } from './brand.model';

@Injectable({
  providedIn: 'root'
})
export class BrandService {

  //baseUrl = "https://supplycontrol.herokuapp.com/brands"
  baseUrl = "http://localhost:8080/brands"
  
  constructor(private snackBar: MatSnackBar, private http: HttpClient) { }

  showMessage(msg: string, isError: boolean = false): void {
    this.snackBar.open(msg, 'X', {
      duration: 3000,
      horizontalPosition: "right",
      verticalPosition: "top",
      panelClass: isError ? ['msg-error'] : ['msg-success']
    })
  }

  create(brand: Brand): Observable<Brand> {
    return this.http.post<Brand>(this.baseUrl, brand).pipe(
      map(obj => obj),
      catchError(e => this.errorHandler(e))
    );
  }

  read(): Observable<Brand[]> {
    return this.http.get<Brand[]>(this.baseUrl).pipe(
      map(obj => obj),
      catchError(e => this.errorHandler(e))
    );
  }

  readById(id: string): Observable<Brand> {
    const url = `${this.baseUrl}/${id}`
    return this.http.get<Brand>(url).pipe(
      map(obj => obj),
      catchError(e => this.errorHandler(e))
    );
  }

  update(brand: Brand): Observable<Brand> {
    const url = `${this.baseUrl}/${brand.id}`
    return this.http.put<Brand>(url, brand).pipe(
      map(obj => obj),
      catchError(e => this.errorHandler(e))
    );
  }

  delete(id: number): Observable<Brand> {
    const url = `${this.baseUrl}/${id}`
    return this.http.delete<Brand>(url).pipe(
      map(obj => obj),
      catchError(e => this.errorHandler(e))
    );
  }

  errorHandler(e: any): Observable<any> {
    this.showMessage('Ocorreu um erro!', true)
    return EMPTY

  }
}
