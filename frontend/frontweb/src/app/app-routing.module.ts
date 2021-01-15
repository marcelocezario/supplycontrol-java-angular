import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './views/home/home.component';
import { BrandCrudComponent } from './views/brand-crud/brand-crud.component'

const routes: Routes = [{
  path: "",
  component: HomeComponent
},
{
  path: "brands",
  component: BrandCrudComponent
}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
