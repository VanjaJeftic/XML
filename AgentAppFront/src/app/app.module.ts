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
import { NovoVoziloComponent } from './components/novo-vozilo/novo-vozilo.component';
import { SvaVozilaComponent } from './components/sva-vozila/sva-vozila.component';
import { NoviOglasComponent } from './components/novi-oglas/novi-oglas.component';
import { NoviOglasService } from './services/novi-oglas.service';
import { NovoVoziloService } from './services/novo-vozilo.service';
import { SvaVozilaService } from './services/sva-vozila.service';



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
    NoviOglasComponent
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
  entryComponents: [NoviOglasComponent,KomentarFormaComponent
  ],

  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi: true
  },
    
    NoviOglasService,
    NovoVoziloService,
    SvaVozilaService
],
  bootstrap: [AppComponent]
})
export class AppModule { }
