import { catchError, map } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { EMPTY, Observable } from 'rxjs';
import { Vehicle } from './vehicle.model';

@Injectable({
  providedIn: 'root'
})
export class VehicleService {

  //baseUrl = "https://supplycontrol.herokuapp.com/vehicles"
  baseUrl = "http://localhost:8080/vehicles"
  
  constructor(private snackBar: MatSnackBar, private http: HttpClient) { }

  showMessage(msg: string, isError: boolean = false): void {
    this.snackBar.open(msg, 'X', {
      duration: 3000,
      horizontalPosition: "right",
      verticalPosition: "top",
      panelClass: isError ? ['msg-error'] : ['msg-success']
    })
  }

  create(vehicle: Vehicle): Observable<Vehicle> {
    return this.http.post<Vehicle>(this.baseUrl, vehicle).pipe(
      map(obj => obj),
      catchError(e => this.errorHandler(e))
    );
  }

  read(): Observable<Vehicle[]> {
    return this.http.get<Vehicle[]>(this.baseUrl).pipe(
      map(obj => obj),
      catchError(e => this.errorHandler(e))
    );
  }

  readById(id: string): Observable<Vehicle> {
    const url = `${this.baseUrl}/${id}`
    return this.http.get<Vehicle>(url).pipe(
      map(obj => obj),
      catchError(e => this.errorHandler(e))
    );
  }

  update(vehicle: Vehicle): Observable<Vehicle> {
    const url = `${this.baseUrl}/${vehicle.id}`
    return this.http.put<Vehicle>(url, vehicle).pipe(
      map(obj => obj),
      catchError(e => this.errorHandler(e))
    );
  }

  delete(id: number): Observable<Vehicle> {
    const url = `${this.baseUrl}/${id}`
    return this.http.delete<Vehicle>(url).pipe(
      map(obj => obj),
      catchError(e => this.errorHandler(e))
    );
  }

  errorHandler(e: any): Observable<any> {
    this.showMessage('Ocorreu um erro!', true)
    return EMPTY

  }
}

