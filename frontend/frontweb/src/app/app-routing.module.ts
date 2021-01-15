import { SupplyDeleteComponent } from './components/supply/supply-delete/supply-delete.component';
import { SupplyUpdateComponent } from './components/supply/supply-update/supply-update.component';
import { SupplyCreateComponent } from './components/supply/supply-create/supply-create.component';
import { SupplyCrudComponent } from './views/supply-crud/supply-crud.component';
import { VehicleDeleteComponent } from './components/vehicle/vehicle-delete/vehicle-delete.component';
import { VehicleUpdateComponent } from './components/vehicle/vehicle-update/vehicle-update.component';
import { VehicleCreateComponent } from './components/vehicle/vehicle-create/vehicle-create.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './views/home/home.component';
import { BrandCrudComponent } from './views/brand-crud/brand-crud.component';
import { BrandCreateComponent } from './components/brand/brand-create/brand-create.component';
import { BrandDeleteComponent } from './components/brand/brand-delete/brand-delete.component';
import { BrandUpdateComponent } from './components/brand/brand-update/brand-update.component';
import { VehiclemodelCrudComponent } from './views/vehiclemodel-crud/vehiclemodel-crud.component';
import { VehiclemodelCreateComponent } from './components/vehiclemodel/vehiclemodel-create/vehiclemodel-create.component';
import { VehiclemodelUpdateComponent } from './components/vehiclemodel/vehiclemodel-update/vehiclemodel-update.component';
import { VehiclemodelDeleteComponent } from './components/vehiclemodel/vehiclemodel-delete/vehiclemodel-delete.component';
import { VehicleCrudComponent } from './views/vehicle-crud/vehicle-crud.component';


const routes: Routes = [{
  path: "",
  component: HomeComponent
},
{
  path: "brands",
  component: BrandCrudComponent
},
{
  path: "brands/create",
  component: BrandCreateComponent
},
{
  path: "brands/update/:id",
  component: BrandUpdateComponent
},
{
  path: "brands/delete/:id",
  component: BrandDeleteComponent
},
{
  path: "vehiclemodels",
  component: VehiclemodelCrudComponent
},
{
  path: "vehiclemodels/create",
  component: VehiclemodelCreateComponent
},
{
  path: "vehiclemodels/update/:id",
  component: VehiclemodelUpdateComponent
},
{
  path: "vehiclemodels/delete/:id",
  component: VehiclemodelDeleteComponent
},
{
  path: "vehicles",
  component: VehicleCrudComponent
},
{
  path: "vehicles/create",
  component: VehicleCreateComponent
},
{
  path: "vehicles/update/:id",
  component: VehicleUpdateComponent
},
{
  path: "vehicles/delete/:id",
  component: VehicleDeleteComponent
},
{
  path: "supplies",
  component: SupplyCrudComponent
},
{
  path: "supplies/create",
  component: SupplyCreateComponent
},
{
  path: "supplies/update/:id",
  component: SupplyUpdateComponent
},
{
  path: "supplies/delete/:id",
  component: SupplyDeleteComponent
}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
