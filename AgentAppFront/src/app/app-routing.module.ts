import { MyShopCartItemsComponent } from './components/oglas/my-shop-cart-items/my-shop-cart-items.component';
import { OglasComponent } from './components/oglas/oglas.component';
import { LoginComponent } from './components/login/login.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'login' },
  { path: 'login', component: LoginComponent },
  { path: 'oglas', component: OglasComponent },
  { path: 'cart', component: MyShopCartItemsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
