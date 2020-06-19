import { IzvestajComponent } from './components/userComponent/agent/izvestaj/izvestaj.component';
import { MojaVozilaComponent } from './components/userComponent/agent/moja-vozila/moja-vozila.component';
import { ZahteviComponent } from './components/userComponent/agent/zahtevi/zahtevi.component';
import { MyShopCartItemsComponent } from './components/userComponent/user/my-shop-cart-items/my-shop-cart-items.component';
import { VoziloDetailsComponent } from './components/userComponent/user/vozilo-details/vozilo-details.component';
import { UserComponent } from './components/userComponent/user/user.component';
import { AgentComponent } from './components/userComponent/agent/agent.component';
import { AdministratorComponent } from './components/userComponent/administrator/administrator.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { LoginComponent } from './components/login/login.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegisterComponent } from './components/register/register.component';
import { ActivationPageComponent } from './components/activation-page/activation-page.component';
import { MarkaVozilaComponent } from './components/userComponent/marka-vozila/marka-vozila.component';
import { KlasaVozilaComponent } from './components/userComponent/klasa-vozila/klasa-vozila.component';
import { TipGorivaComponent } from './components/userComponent/tip-goriva/tip-goriva.component';
import { VrstaMenjacaComponent } from './components/userComponent/vrsta-menjaca/vrsta-menjaca.component';
import { ModelVozilaComponent } from './components/userComponent/model-vozila/model-vozila.component';
import { PrikazSvihMarkiVozilaComponent } from './components/userComponent/marka-vozila/prikaz-svih-marki-vozila/prikaz-svih-marki-vozila.component';
import { PrikazModelaVozilaComponent } from './components/userComponent/model-vozila/prikaz-modela-vozila/prikaz-modela-vozila.component';
import { PrikazSvihKlasaVozilaComponent } from './components/userComponent/klasa-vozila/prikaz-svih-klasa-vozila/prikaz-svih-klasa-vozila.component';
import { PrikazSvihTipovaGorivaComponent } from './components/userComponent/tip-goriva/prikaz-svih-tipova-goriva/prikaz-svih-tipova-goriva.component';
import { IzmenaMarkeVozilaComponent } from './components/userComponent/marka-vozila/izmena-marke-vozila/izmena-marke-vozila.component';
import { IzmenaKlaseVozilaComponent } from './components/userComponent/klasa-vozila/izmena-klase-vozila/izmena-klase-vozila.component';
import { PrikazSvihVrstaMenjacaComponent } from './components/userComponent/vrsta-menjaca/prikaz-svih-vrsta-menjaca/prikaz-svih-vrsta-menjaca.component';
import { IzmenaVrstaMenjacaComponent } from './components/userComponent/vrsta-menjaca/izmena-vrsta-menjaca/izmena-vrsta-menjaca.component';
import { IzmenaTipaGorivaComponent } from './components/userComponent/tip-goriva/izmena-tipa-goriva/izmena-tipa-goriva.component';
import { IzmenaModelaVozilaComponent } from './components/userComponent/model-vozila/izmena-modela-vozila/izmena-modela-vozila.component';
import { NovoVoziloComponent } from './components/novo-vozilo/novo-vozilo.component';
import { SvaVozilaComponent } from './components/sva-vozila/sva-vozila.component';
import { ResetLozinkeComponent } from './components/reset-lozinke/reset-lozinke.component';
import { PorukaZahtevComponent } from './components/userComponent/user/poruka-zahtev/poruka-zahtev.component';
import { PorukeRazmenjeneComponent } from './components/userComponent/user/poruke-razmenjene/poruke-razmenjene.component';
import { PrikazPorukaComponent } from './components/userComponent/user/prikaz-poruka/prikaz-poruka.component';


const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'home', component: HomePageComponent },
  { path: 'login', component: LoginComponent },
  { path: 'administrator', component:  AdministratorComponent },
  { path: 'agent', component: AgentComponent },
  { path: 'user', component: UserComponent},
  { path: 'cart', component: MyShopCartItemsComponent },
  { path: 'vozilo/:id', component: VoziloDetailsComponent},
  { path: 'register', component: RegisterComponent},
  {path: 'activateAccount/:id',component: ActivationPageComponent},

  { path: 'markaVozila', component: MarkaVozilaComponent},
  { path: 'klasaVozila', component: KlasaVozilaComponent},
  { path: 'gorivoVozila', component:TipGorivaComponent},
  { path: 'menjacVozila', component:VrstaMenjacaComponent},
  { path: 'modelVozila', component:ModelVozilaComponent},
  { path: 'listaMarkiVozila', component:PrikazSvihMarkiVozilaComponent},
  { path: 'listaModelaVozila', component:PrikazModelaVozilaComponent},
  { path: 'listaKlasaVozila', component:PrikazSvihKlasaVozilaComponent},
  { path: 'listaTipaGorivaVozila', component:PrikazSvihTipovaGorivaComponent},
  { path: 'listaVrstaMenjacaVozila', component:PrikazSvihVrstaMenjacaComponent},
  { path: 'izmenaMarkeVozila', component:IzmenaMarkeVozilaComponent},
  { path: 'izmenaKlaseVozila', component:IzmenaKlaseVozilaComponent},
  { path: 'izmenaTipaGorivaVozila', component:IzmenaTipaGorivaComponent},
  { path: 'izmenaVrsteMenjacaVozila', component:IzmenaVrstaMenjacaComponent},
  { path: 'izmenaModelaVozila', component:IzmenaModelaVozilaComponent},
  { path: 'novoVozilo', component:NovoVoziloComponent},
  { path: 'svaVozila',component:SvaVozilaComponent},
  { path: 'promenaLozinke', component: ResetLozinkeComponent },
  { path: 'agent/zahtev', component: ZahteviComponent },
  { path: 'agent/vozilo', component: MojaVozilaComponent},
  { path: 'agent/izvestaj', component: IzvestajComponent },
  { path: 'posalji', component:PorukaZahtevComponent},
  { path: 'zahteviMess', component:PorukeRazmenjeneComponent},
  { path: 'prikazPoruke',component:PrikazPorukaComponent},



];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
