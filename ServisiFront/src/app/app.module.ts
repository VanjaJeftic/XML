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
import { Shared } from './models/shared';
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
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    AngularMaterialModule,
    FormsModule,
    HttpClientModule,
    NgxMaterialTimepickerModule,
    ReactiveFormsModule
  ],
  entryComponents: [TerminZauzecaDialogComponent],
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
