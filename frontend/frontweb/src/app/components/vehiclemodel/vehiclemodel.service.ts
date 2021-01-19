import { VehicleModel } from './vehiclemodel.model';
import { catchError, map } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { EMPTY, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class VehicleModelService {

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

  create(vehiclemodel: VehicleModel): Observable<VehicleModel> {
    return this.http.post<VehicleModel>(this.baseUrl, vehiclemodel).pipe(
      map(obj => obj),
      catchError(e => this.errorHandler(e))
    );
  }

  read(): Observable<VehicleModel[]> {
    return this.http.get<VehicleModel[]>(this.baseUrl).pipe(
      map(obj => obj),
      catchError(e => this.errorHandler(e))
    );
  }

  readByBrand(idBrand: number): Observable<VehicleModel[]> {

    let url = this.baseUrl + '/filter?idBrand=' + idBrand

    console.log(url)

    return this.http.get<VehicleModel[]>(url).pipe(
      map(obj => obj),
      catchError(e => this.errorHandler(e))
    );
  }

  readById(id: string): Observable<VehicleModel> {
    const url = `${this.baseUrl}/${id}`
    return this.http.get<VehicleModel>(url).pipe(
      map(obj => obj),
      catchError(e => this.errorHandler(e))
    );
  }

  update(vehiclemodel: VehicleModel): Observable<VehicleModel> {
    const url = `${this.baseUrl}/${vehiclemodel.id}`
    return this.http.put<VehicleModel>(url, vehiclemodel).pipe(
      map(obj => obj),
      catchError(e => this.errorHandler(e))
    );
  }

  delete(id: number): Observable<VehicleModel> {
    const url = `${this.baseUrl}/${id}`
    return this.http.delete<VehicleModel>(url).pipe(
      map(obj => obj),
      catchError(e => this.errorHandler(e))
    );
  }

  errorHandler(e: any): Observable<any> {
    this.showMessage('Ocorreu um erro!', true)
    return EMPTY

  }
}

