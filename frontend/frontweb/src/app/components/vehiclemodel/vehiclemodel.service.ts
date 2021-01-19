import { catchError, map } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { EMPTY, Observable } from 'rxjs';
import { Vehiclemodel } from './vehiclemodel.model';

@Injectable({
  providedIn: 'root'
})
export class VehiclemodelService {

  baseUrl = "https://supplycontrol.herokuapp.com/vehiclemodels"

  constructor(private snackBar: MatSnackBar, private http: HttpClient) { }

  showMessage(msg: string, isError: boolean = false): void {
    this.snackBar.open(msg, 'X', {
      duration: 3000,
      horizontalPosition: "right",
      verticalPosition: "top",
      panelClass: isError ? ['msg-error'] : ['msg-success']
    })
  }

  create(vehiclemodel: Vehiclemodel): Observable<Vehiclemodel> {
    return this.http.post<Vehiclemodel>(this.baseUrl, vehiclemodel).pipe(
      map(obj => obj),
      catchError(e => this.errorHandler(e))
    );
  }

  read(): Observable<Vehiclemodel[]> {
    return this.http.get<Vehiclemodel[]>(this.baseUrl).pipe(
      map(obj => obj),
      catchError(e => this.errorHandler(e))
    );
  }

  readByBrand(idBrand: number): Observable<Vehiclemodel[]> {
    return this.http.get<Vehiclemodel[]>(this.baseUrl + 'filter?idBrand=' + idBrand).pipe(
      map(obj => obj),
      catchError(e => this.errorHandler(e))
    );
  }

  readById(id: string): Observable<Vehiclemodel> {
    const url = `${this.baseUrl}/${id}`
    return this.http.get<Vehiclemodel>(url).pipe(
      map(obj => obj),
      catchError(e => this.errorHandler(e))
    );
  }

  update(vehiclemodel: Vehiclemodel): Observable<Vehiclemodel> {
    const url = `${this.baseUrl}/${vehiclemodel.id}`
    return this.http.put<Vehiclemodel>(url, vehiclemodel).pipe(
      map(obj => obj),
      catchError(e => this.errorHandler(e))
    );
  }

  delete(id: number): Observable<Vehiclemodel> {
    const url = `${this.baseUrl}/${id}`
    return this.http.delete<Vehiclemodel>(url).pipe(
      map(obj => obj),
      catchError(e => this.errorHandler(e))
    );
  }

  errorHandler(e: any): Observable<any> {
    this.showMessage('Ocorreu um erro!', true)
    return EMPTY

  }
}

