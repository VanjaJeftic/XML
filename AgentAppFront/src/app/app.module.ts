import { TokenInterceptor } from './interceptor/TokenInterceptor';
import { AngularMaterialModule } from './angular-material.module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {NgxMaterialTimepickerModule} from 'ngx-material-timepicker';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './components/login/login.component';
import { FormsModule } from '@angular/forms';
import { UserComponent } from './components/userComponent/user/user.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AgentComponent } from './components/userComponent/agent/agent.component';
import { AdministratorComponent } from './components/userComponent/administrator/administrator.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { VoziloDetailsComponent } from './components/userComponent/user/vozilo-details/vozilo-details.component';
import { MyShopCartItemsComponent } from './components/userComponent/user/my-shop-cart-items/my-shop-cart-items.component';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { RegisterComponent } from './components/register/register.component';
import { ActivationPageComponent } from './components/activation-page/activation-page.component';
import { KlasaVozilaComponent } from './components/userComponent/klasa-vozila/klasa-vozila.component';
import { IzmenaKlaseVozilaComponent } from './components/userComponent/klasa-vozila/izmena-klase-vozila/izmena-klase-vozila.component';
import { PrikazSvihKlasaVozilaComponent } from './components/userComponent/klasa-vozila/prikaz-svih-klasa-vozila/prikaz-svih-klasa-vozila.component';
import { MarkaVozilaComponent } from './components/userComponent/marka-vozila/marka-vozila.component';
import { IzmenaMarkeVozilaComponent } from './components/userComponent/marka-vozila/izmena-marke-vozila/izmena-marke-vozila.component';
import { PrikazSvihMarkiVozilaComponent } from './components/userComponent/marka-vozila/prikaz-svih-marki-vozila/prikaz-svih-marki-vozila.component';
import { ModelVozilaComponent } from './components/userComponent/model-vozila/model-vozila.component';
import { IzmenaModelaVozilaComponent } from './components/userComponent/model-vozila/izmena-modela-vozila/izmena-modela-vozila.component';
import { PrikazModelaVozilaComponent } from './components/userComponent/model-vozila/prikaz-modela-vozila/prikaz-modela-vozila.component';
import { TipGorivaComponent } from './components/userComponent/tip-goriva/tip-goriva.component';
import { IzmenaTipaGorivaComponent } from './components/userComponent/tip-goriva/izmena-tipa-goriva/izmena-tipa-goriva.component';
import { PrikazSvihTipovaGorivaComponent } from './components/userComponent/tip-goriva/prikaz-svih-tipova-goriva/prikaz-svih-tipova-goriva.component';
import { VrstaMenjacaComponent } from './components/userComponent/vrsta-menjaca/vrsta-menjaca.component';
import { IzmenaVrstaMenjacaComponent } from './components/userComponent/vrsta-menjaca/izmena-vrsta-menjaca/izmena-vrsta-menjaca.component';
import { PrikazSvihVrstaMenjacaComponent } from './components/userComponent/vrsta-menjaca/prikaz-svih-vrsta-menjaca/prikaz-svih-vrsta-menjaca.component';
import { MatMenuModule, MatDialogModule, MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { KomentarFormaComponent } from './components/userComponent/komentar-forma/komentar-forma.component';
//import { MatMenuModule, MatDialogModule } from '@angular/material';
import { NovoVoziloComponent } from './components/novo-vozilo/novo-vozilo.component';
import { SvaVozilaComponent } from './components/sva-vozila/sva-vozila.component';
import { NoviOglasComponent } from './components/novi-oglas/novi-oglas.component';
import { NoviOglasService } from './services/novi-oglas.service';
import { NovoVoziloService } from './services/novo-vozilo.service';
import { SvaVozilaService } from './services/sva-vozila.service';
import { ZahteviComponent } from './components/userComponent/agent/zahtevi/zahtevi.component';
import { MojaVozilaComponent } from './components/userComponent/agent/moja-vozila/moja-vozila.component';
import { IzvestajComponent } from './components/userComponent/agent/izvestaj/izvestaj.component';
import { TerminZauzecaDialogComponent } from './components/userComponent/agent/moja-vozila/termin-zauzeca-dialog/termin-zauzeca-dialog.component';
import { IzvestajDialogComponent } from './components/userComponent/agent/izvestaj/izvestaj-dialog/izvestaj-dialog.component';
import { ResetLozinkeComponent } from './components/reset-lozinke/reset-lozinke.component';
import { PorukaZahtevComponent } from './components/userComponent/user/poruka-zahtev/poruka-zahtev.component';
import { PorukaComponent } from './components/userComponent/user/poruka-zahtev/poruka/poruka.component';
import { PorukaService } from './services/poruka.service';
import { PrikazPorukaComponent } from './components/userComponent/user/prikaz-poruka/prikaz-poruka.component';
import { PorukeRazmenjeneComponent } from './components/userComponent/user/poruke-razmenjene/poruke-razmenjene.component';
import { PrikazKomentaraKorisnikaComponent } from './components/userComponent/prikaz-komentara-korisnika/prikaz-komentara-korisnika.component';
import { DijalogInfoKorisnikaComponent } from './components/userComponent/dijalog-info-korisnika/dijalog-info-korisnika.component';
import { ListOdobrenihKomentaraAdminComponent } from './components/userComponent/list-odobrenih-komentara-admin/list-odobrenih-komentara-admin.component';
import { ListaOdbijenihKomentaraAdminComponent } from './components/userComponent/lista-odbijenih-komentara-admin/lista-odbijenih-komentara-admin.component';
import { SviKomentariOglasaDijalogComponent } from './components/userComponent/svi-komentari-oglasa-dijalog/svi-komentari-oglasa-dijalog.component';
import { DijalogRegistracijaAgentaComponent } from './components/userComponent/dijalog-registracija-agenta/dijalog-registracija-agenta.component';
import { DijalogRegistracijaFirmeComponent } from './components/userComponent/dijalog-registracija-firme/dijalog-registracija-firme.component';
import { KorisniciComponent } from './components/userComponent/administrator/korisnici/korisnici.component';
import { CenovniciComponent } from './components/userComponent/cenovnici/cenovnici.component';
import { CenovnikComponent } from './components/userComponent/cenovnici/cenovnik/cenovnik.component';
import { StavkaCenovnikaComponent } from './components/userComponent/cenovnici/stavka-cenovnika/stavka-cenovnika.component';
import { SviOglasiComponent } from './components/svi-oglasi/svi-oglasi.component';

import { RezervacijaDialogComponent } from './components/userComponent/user/vozilo-details/rezervacija-dialog/rezervacija-dialog.component';

import { IzmeniCenovnikComponent } from './components/userComponent/cenovnici/izmeni-cenovnik/izmeni-cenovnik.component';
import { IzmenaVozilaComponent } from './components/sva-vozila/izmena-vozila/izmena-vozila.component';
import { IzmeniOglasComponent } from './components/svi-oglasi/izmeni-oglas/izmeni-oglas.component';
import { IzmeniStavkuComponent } from './components/userComponent/cenovnici/izmeni-stavku/izmeni-stavku.component';
import { StavkeComponent } from './components/userComponent/cenovnici/stavke/stavke.component';





@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    UserComponent,
    AgentComponent,
    AdministratorComponent,
    HomePageComponent,
    VoziloDetailsComponent,
    MyShopCartItemsComponent,
    RegisterComponent,
    ActivationPageComponent,
    KlasaVozilaComponent,
    IzmenaKlaseVozilaComponent,
    PrikazSvihKlasaVozilaComponent,
    MarkaVozilaComponent,
    IzmenaMarkeVozilaComponent,
    PrikazSvihMarkiVozilaComponent,
    ModelVozilaComponent,
    IzmenaModelaVozilaComponent,
    PrikazModelaVozilaComponent,
    TipGorivaComponent,
    IzmenaTipaGorivaComponent,
    PrikazSvihTipovaGorivaComponent,
    VrstaMenjacaComponent,
    IzmenaVrstaMenjacaComponent,
    PrikazSvihVrstaMenjacaComponent,
    KomentarFormaComponent,
    NovoVoziloComponent,
    SvaVozilaComponent,
    NoviOglasComponent,
    ZahteviComponent,
    MojaVozilaComponent,
    IzvestajComponent,
    TerminZauzecaDialogComponent,
    IzvestajDialogComponent,
    ResetLozinkeComponent,
    PorukaZahtevComponent,
    PorukaComponent,
    PrikazPorukaComponent,
    PorukeRazmenjeneComponent,
    PrikazKomentaraKorisnikaComponent,
    DijalogInfoKorisnikaComponent,
    ListOdobrenihKomentaraAdminComponent,
    ListaOdbijenihKomentaraAdminComponent,
    SviKomentariOglasaDijalogComponent,
    DijalogRegistracijaAgentaComponent,
    DijalogRegistracijaFirmeComponent,
    KorisniciComponent,
    CenovniciComponent,
    CenovnikComponent,
    StavkaCenovnikaComponent,
    SviOglasiComponent,
    RezervacijaDialogComponent,
    IzmeniCenovnikComponent,
    IzmenaVozilaComponent,
    IzmeniOglasComponent,
    IzmeniStavkuComponent,
    StavkeComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    AngularMaterialModule,
    FormsModule,
    HttpClientModule,
    NgxMaterialTimepickerModule,
    OwlDateTimeModule, 
    OwlNativeDateTimeModule,
    MatMenuModule,
    MatDialogModule
  
  ],

  entryComponents: [NoviOglasComponent,KomentarFormaComponent, TerminZauzecaDialogComponent, IzvestajDialogComponent,DijalogInfoKorisnikaComponent,SviKomentariOglasaDijalogComponent,PorukaComponent,
    DijalogRegistracijaAgentaComponent, DijalogRegistracijaFirmeComponent,CenovnikComponent,StavkaCenovnikaComponent, RezervacijaDialogComponent

  ],

  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi: true
  },
    
    NoviOglasService,
    NovoVoziloService,
    SvaVozilaService,
    PorukaService,
],
  bootstrap: [AppComponent]
})
export class AppModule { }
