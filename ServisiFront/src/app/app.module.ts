import { TokenInterceptor } from './interceptor/TokenInterceptor';
import { AngularMaterialModule } from './angular-material.module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NgxMaterialTimepickerModule } from 'ngx-material-timepicker';
import { FormGroup,ReactiveFormsModule } from '@angular/forms';
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
import { NoviOglasComponent } from './components/userComponent/user/novi-oglas/novi-oglas.component';
import { NoviOglasService } from './services/novi-oglas.service';
import { PraviOglasComponent } from './components/userComponent/user/pravi-oglas/pravi-oglas.component'
import { PraviOglasService } from './services/pravi-oglas.service';
import { MyShopCartItemsComponent } from './components/userComponent/user/my-shop-cart-items/my-shop-cart-items.component';
import { VoziloDetailsComponent } from './components/userComponent/user/vozilo-details/vozilo-details.component';
import { MojaVozilaComponent } from './components/userComponent/agent/moja-vozila/moja-vozila.component';
import { TerminZauzecaDialogComponent } from './components/userComponent/agent/termin-zauzeca-dialog/termin-zauzeca-dialog.component';
import { MarkaVozilaComponent } from './components/userComponent/marka-vozila/marka-vozila.component';
import { TipGorivaComponent } from './components/userComponent/tip-goriva/tip-goriva.component';
import { VrstaMenjacaComponent } from './components/userComponent/vrsta-menjaca/vrsta-menjaca.component';
import { KlasaVozilaComponent } from './components/userComponent/klasa-vozila/klasa-vozila.component';
import { ModelVozilaComponent } from './components/userComponent/model-vozila/model-vozila.component';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { Shared } from './models/shared';
import { RegistrationPageComponent } from './components/registration-page/registration-page.component';
import { MatMenuModule} from '@angular/material/menu';
import { PrikazSvihMarkiVozilaComponent } from './components/userComponent/marka-vozila/prikaz-svih-marki-vozila/prikaz-svih-marki-vozila.component';
import { PrikazModelaVozilaComponent } from './components/userComponent/model-vozila/prikaz-modela-vozila/prikaz-modela-vozila.component';
import { PrikazSvihTipovaGorivaComponent } from './components/userComponent/tip-goriva/prikaz-svih-tipova-goriva/prikaz-svih-tipova-goriva.component';
import { PrikazSvihVrstaMenjacaComponent } from './components/userComponent/vrsta-menjaca/prikaz-svih-vrsta-menjaca/prikaz-svih-vrsta-menjaca.component';
import { PrikazSvihKlasaVozilaComponent } from './components/userComponent/klasa-vozila/prikaz-svih-klasa-vozila/prikaz-svih-klasa-vozila.component';
import { IzmenaMarkeVozilaComponent } from './components/userComponent/marka-vozila/izmena-marke-vozila/izmena-marke-vozila.component';
import { MatDialogModule } from '@angular/material';
import { IzmenaKlaseVozilaComponent } from './components/userComponent/klasa-vozila/izmena-klase-vozila/izmena-klase-vozila.component';
import { IzmenaTipaGorivaComponent } from './components/userComponent/tip-goriva/izmena-tipa-goriva/izmena-tipa-goriva.component';
import { IzmenaVrstaMenjacaComponent } from './components/userComponent/vrsta-menjaca/izmena-vrsta-menjaca/izmena-vrsta-menjaca.component';
import { IzmenaModelaVozilaComponent } from './components/userComponent/model-vozila/izmena-modela-vozila/izmena-modela-vozila.component';
import { SvaVozilaComponent } from './components/userComponent/user/sva-vozila/sva-vozila.component';
import { ZahteviComponent } from './components/userComponent/agent/zahtevi/zahtevi.component';
import { IzvestajComponent } from './components/userComponent/agent/izvestaj/izvestaj.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    UserComponent,
    AgentComponent,
    AdministratorComponent,
    HomePageComponent,
    NoviOglasComponent,
    PraviOglasComponent,
    MyShopCartItemsComponent,
    VoziloDetailsComponent,
    MojaVozilaComponent,
    TerminZauzecaDialogComponent,
    MarkaVozilaComponent,
    TipGorivaComponent,
    VrstaMenjacaComponent,
    KlasaVozilaComponent,
    ModelVozilaComponent,
    RegistrationPageComponent,
    PrikazSvihMarkiVozilaComponent,
    PrikazModelaVozilaComponent,
    PrikazSvihTipovaGorivaComponent,
    PrikazSvihVrstaMenjacaComponent,
    PrikazSvihKlasaVozilaComponent,
    IzmenaMarkeVozilaComponent,
    IzmenaKlaseVozilaComponent,
    IzmenaTipaGorivaComponent,
    IzmenaVrstaMenjacaComponent,
    IzmenaModelaVozilaComponent,
    SvaVozilaComponent,
    ZahteviComponent,
    IzvestajComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    AngularMaterialModule,
    FormsModule,
    HttpClientModule,
    NgxMaterialTimepickerModule,
    ReactiveFormsModule,
    OwlDateTimeModule, 
    OwlNativeDateTimeModule,
    MatMenuModule,
    MatDialogModule,

  ],
  entryComponents: [TerminZauzecaDialogComponent
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi: true
  },
    NoviOglasService,
    PraviOglasService,
    Shared
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
