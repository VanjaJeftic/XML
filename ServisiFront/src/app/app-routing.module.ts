import { MojaVozilaComponent } from './components/userComponent/agent/moja-vozila/moja-vozila.component';
import { VoziloDetailsComponent } from './components/userComponent/user/vozilo-details/vozilo-details.component';
import { UserComponent } from './components/userComponent/user/user.component';
import { AgentComponent } from './components/userComponent/agent/agent.component';
import { AdministratorComponent } from './components/userComponent/administrator/administrator.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { LoginComponent } from './components/login/login.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NoviOglasComponent } from './components/userComponent/user/novi-oglas/novi-oglas.component';
import { PraviOglasComponent } from './components/userComponent/user/pravi-oglas/pravi-oglas.component';
import { MyShopCartItemsComponent } from './components/userComponent/user/my-shop-cart-items/my-shop-cart-items.component';
import { MarkaVozilaComponent } from './components/userComponent/marka-vozila/marka-vozila.component';
import { KlasaVozilaComponent } from './components/userComponent/klasa-vozila/klasa-vozila.component';
import { TipGorivaComponent } from './components/userComponent/tip-goriva/tip-goriva.component';
import { VrstaMenjacaComponent } from './components/userComponent/vrsta-menjaca/vrsta-menjaca.component';
import { ModelVozilaComponent } from './components/userComponent/model-vozila/model-vozila.component';
import { RegistrationPageComponent } from './components/registration-page/registration-page.component';


const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'home', component: HomePageComponent },
  { path: 'login', component: LoginComponent },
  { path: 'administrator', component:  AdministratorComponent },
  { path: 'agent', component: AgentComponent },
  { path: 'user', component: UserComponent },
  { path: 'vozilo/novoVozilo', component: NoviOglasComponent },
  { path: 'vozilo/:id', component: VoziloDetailsComponent },
  { path: 'vozila', component: MojaVozilaComponent },
  { path: 'oglas/create', component: PraviOglasComponent },
  { path: 'cart', component: MyShopCartItemsComponent },
  { path: 'markaVozila', component: MarkaVozilaComponent},
  { path: 'klasaVozila', component: KlasaVozilaComponent},
  { path: 'gorivoVozila', component:TipGorivaComponent},
  { path: 'menjacVozila', component:VrstaMenjacaComponent},
  { path: 'modelVozila', component:ModelVozilaComponent},
  { path: 'register', component:RegistrationPageComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
