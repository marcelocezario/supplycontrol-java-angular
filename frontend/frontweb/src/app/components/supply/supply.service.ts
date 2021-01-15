import { catchError, map } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { EMPTY, Observable } from 'rxjs';
import { Supply } from './supply.model';

@Injectable({
  providedIn: 'root'
})
export class SupplyService {

  baseUrl = "http://localhost:8080/supplies"

  constructor(private snackBar: MatSnackBar, private http: HttpClient) { }

  showMessage(msg: string, isError: boolean = false): void {
    this.snackBar.open(msg, 'X', {
      duration: 3000,
      horizontalPosition: "right",
      verticalPosition: "top",
      panelClass: isError ? ['msg-error'] : ['msg-success']
    })
  }

  create(supply: Supply): Observable<Supply> {
    return this.http.post<Supply>(this.baseUrl, supply).pipe(
      map(obj => obj),
      catchError(e => this.errorHandler(e))
    );
  }

  read(): Observable<Supply[]> {
    return this.http.get<Supply[]>(this.baseUrl).pipe(
      map(obj => obj),
      catchError(e => this.errorHandler(e))
    );
  }

  readById(id: string): Observable<Supply> {
    const url = `${this.baseUrl}/${id}`
    return this.http.get<Supply>(url).pipe(
      map(obj => obj),
      catchError(e => this.errorHandler(e))
    );
  }

  update(supply: Supply): Observable<Supply> {
    const url = `${this.baseUrl}/${supply.id}`
    return this.http.put<Supply>(url, supply).pipe(
      map(obj => obj),
      catchError(e => this.errorHandler(e))
    );
  }

  delete(id: number): Observable<Supply> {
    const url = `${this.baseUrl}/${id}`
    return this.http.delete<Supply>(url).pipe(
      map(obj => obj),
      catchError(e => this.errorHandler(e))
    );
  }

  errorHandler(e: any): Observable<any> {
    this.showMessage('Ocorreu um erro!', true)
    return EMPTY

  }
}
