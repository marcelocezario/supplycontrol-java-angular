import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavComponent } from './components/template/nav/nav.component';
import { HomeComponent } from './views/home/home.component';
import { BrandCreateComponent } from './components/brand/brand-create/brand-create.component';
import { BrandDeleteComponent } from './components/brand/brand-delete/brand-delete.component';
import { BrandReadComponent } from './components/brand/brand-read/brand-read.component';
import { BrandUpdateComponent } from './components/brand/brand-update/brand-update.component';
import { BrandCrudComponent } from './views/brand-crud/brand-crud.component';
import { VehicleCreateComponent } from './components/vehicle/vehicle-create/vehicle-create.component';
import { VehicleDeleteComponent } from './components/vehicle/vehicle-delete/vehicle-delete.component';
import { VehicleReadComponent } from './components/vehicle/vehicle-read/vehicle-read.component';
import { VehicleUpdateComponent } from './components/vehicle/vehicle-update/vehicle-update.component';
import { VehicleCrudComponent } from './views/vehicle-crud/vehicle-crud.component';
import { VehiclemodelCreateComponent } from './components/vehiclemodel/vehiclemodel-create/vehiclemodel-create.component';
import { VehiclemodelDeleteComponent } from './components/vehiclemodel/vehiclemodel-delete/vehiclemodel-delete.component';
import { VehiclemodelReadComponent } from './components/vehiclemodel/vehiclemodel-read/vehiclemodel-read.component';
import { VehiclemodelUpdateComponent } from './components/vehiclemodel/vehiclemodel-update/vehiclemodel-update.component';
import { VehiclemodelCrudComponent } from './views/vehiclemodel-crud/vehiclemodel-crud.component';
import { SupplyCreateComponent } from './components/supply/supply-create/supply-create.component';
import { SupplyDeleteComponent } from './components/supply/supply-delete/supply-delete.component';
import { SupplyReadComponent } from './components/supply/supply-read/supply-read.component';
import { SupplyUpdateComponent } from './components/supply/supply-update/supply-update.component';
import { SupplyCrudComponent } from './views/supply-crud/supply-crud.component';
import { FooterComponent } from './components/template/footer/footer.component';
import { HeaderComponent } from './components/template/header/header.component';

import { NgModule, LOCALE_ID } from '@angular/core';

import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatMenuModule } from '@angular/material/menu';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';

import { registerLocaleData } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import localePt from '@angular/common/locales/pt';

import { FormsModule } from '@angular/forms';
import { MenuComponent } from './components/template/menu/menu.component';
import { BrandCardComponent } from './components/brand/brand-card/brand-card.component';



registerLocaleData(localePt);

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    NavComponent,
    HomeComponent,
    BrandCrudComponent,
    BrandCreateComponent,
    BrandReadComponent,
    BrandUpdateComponent,
    BrandDeleteComponent,
    VehiclemodelCreateComponent,
    VehiclemodelDeleteComponent,
    VehiclemodelReadComponent,
    VehiclemodelUpdateComponent,
    VehicleCreateComponent,
    VehicleDeleteComponent,
    VehicleReadComponent,
    VehicleUpdateComponent,
    SupplyCreateComponent,
    SupplyDeleteComponent,
    SupplyReadComponent,
    SupplyUpdateComponent,
    SupplyCrudComponent,
    VehicleCrudComponent,
    VehiclemodelCrudComponent,
    MenuComponent,
    BrandCardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatSidenavModule,
    MatListModule,
    MatCardModule,
    MatButtonModule,
    MatSnackBarModule,
    HttpClientModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatIconModule,
    MatMenuModule
  ],
  providers: [{
    provide: LOCALE_ID,
    useValue: 'pt-BR'
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
