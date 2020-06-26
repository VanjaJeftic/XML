import { MojiIzvestajiComponent } from './components/userComponent/user/moji-izvestaji/moji-izvestaji.component';
import { MojiZahteviComponent } from './components/userComponent/user/moji-zahtevi/moji-zahtevi.component';
import { IzvestajComponent } from './components/userComponent/agent/izvestaj/izvestaj.component';
import { ZahteviComponent } from './components/userComponent/agent/zahtevi/zahtevi.component';
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
import { PrikazSvihMarkiVozilaComponent } from './components/userComponent/marka-vozila/prikaz-svih-marki-vozila/prikaz-svih-marki-vozila.component';
import { PrikazModelaVozilaComponent } from './components/userComponent/model-vozila/prikaz-modela-vozila/prikaz-modela-vozila.component';
import { PrikazSvihKlasaVozilaComponent } from './components/userComponent/klasa-vozila/prikaz-svih-klasa-vozila/prikaz-svih-klasa-vozila.component';
import { PrikazSvihTipovaGorivaComponent } from './components/userComponent/tip-goriva/prikaz-svih-tipova-goriva/prikaz-svih-tipova-goriva.component';
import { PrikazSvihVrstaMenjacaComponent } from './components/userComponent/vrsta-menjaca/prikaz-svih-vrsta-menjaca/prikaz-svih-vrsta-menjaca.component';
import { IzmenaMarkeVozilaComponent } from './components/userComponent/marka-vozila/izmena-marke-vozila/izmena-marke-vozila.component';
import { IzmenaKlaseVozilaComponent } from './components/userComponent/klasa-vozila/izmena-klase-vozila/izmena-klase-vozila.component';
import { IzmenaTipaGorivaComponent } from './components/userComponent/tip-goriva/izmena-tipa-goriva/izmena-tipa-goriva.component';
import { IzmenaVrstaMenjacaComponent } from './components/userComponent/vrsta-menjaca/izmena-vrsta-menjaca/izmena-vrsta-menjaca.component';
import { IzmenaModelaVozilaComponent } from './components/userComponent/model-vozila/izmena-modela-vozila/izmena-modela-vozila.component';
import { SvaVozilaComponent } from './components/userComponent/user/sva-vozila/sva-vozila.component';
import { PostaviOglasComponent } from './components/userComponent/agent/postavi-oglas/postavi-oglas.component';
import { IznajmljenaVozilaComponent } from './components/userComponent/user/iznajmljena-vozila/iznajmljena-vozila.component';
import { ListOdobrenihKomentaraAdminComponent } from './components/userComponent/list-odobrenih-komentara-admin/list-odobrenih-komentara-admin.component';
import { ListaOdbijenihKomentaraAdminComponent } from './components/userComponent/lista-odbijenih-komentara-admin/lista-odbijenih-komentara-admin.component';
import { PrikazKomentaraKorisnikaComponent } from './components/userComponent/prikaz-komentara-korisnika/prikaz-komentara-korisnika.component';
import { PorukeRazmenjeneComponent } from './components/userComponent/poruke-razmenjene/poruke-razmenjene.component';
import { PrikazPorukaComponent } from './components/userComponent/prikaz-poruka/prikaz-poruka.component';



const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'home', component: HomePageComponent },
  { path: 'login', component: LoginComponent },
  { path: 'administrator', component:  AdministratorComponent },
  { path: 'agent', component: AgentComponent },
  { path: 'user', component: UserComponent },
  { path: 'user/cart', component: MyShopCartItemsComponent },
  { path: 'user/zahtev', component: MojiZahteviComponent },
  { path: 'user/izvestaj', component: MojiIzvestajiComponent },
  { path: 'user/iznajmljena-vozila', component: IznajmljenaVozilaComponent },
  
  { path: 'vozilo/novoVozilo', component: NoviOglasComponent },
  { path: 'vozilo/:id', component: VoziloDetailsComponent },

  { path: 'agent/vozila', component: MojaVozilaComponent },
  { path: 'agent/zahtev', component: ZahteviComponent },
  { path: 'agent/izvestaj', component: IzvestajComponent },

  { path: 'oglas/create', component: PraviOglasComponent },

  { path: 'markaVozila', component: MarkaVozilaComponent},
  { path: 'klasaVozila', component: KlasaVozilaComponent},
  { path: 'gorivoVozila', component:TipGorivaComponent},
  { path: 'menjacVozila', component:VrstaMenjacaComponent},
  { path: 'modelVozila', component:ModelVozilaComponent},
  { path: 'register', component:RegistrationPageComponent},
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
  { path: 'svavozila',component:SvaVozilaComponent},
  { path: 'agent/oglas',component:PostaviOglasComponent},
  {path: 'listaKomentaraAdmina', component:PrikazKomentaraKorisnikaComponent},
  {path: 'listaOdbobrenihKomentaraAdmina',component:ListOdobrenihKomentaraAdminComponent},
  {path: 'listaOdbijenihKomentaraAdmina',component:ListaOdbijenihKomentaraAdminComponent},
  { path: 'zahteviMess', component:PorukeRazmenjeneComponent},
  { path: 'prikazPoruke',component:PrikazPorukaComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
