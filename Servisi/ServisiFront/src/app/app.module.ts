import { TokenInterceptor } from './interceptor/TokenInterceptor';
import { AngularMaterialModule } from './angular-material.module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

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
import { NoviOglasService } from './services/novi-oglas.service'

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    UserComponent,
    AgentComponent,
    AdministratorComponent,
    HomePageComponent,
    NoviOglasComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    AngularMaterialModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi: true
  },
    NoviOglasService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
