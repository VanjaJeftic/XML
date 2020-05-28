import { AngularMaterialModule } from './angular-material.module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './components/login/login.component';
import { FormsModule } from '@angular/forms';
import { UserComponent } from './components/userComponent/user/user.component';
import { HttpClientModule } from '@angular/common/http';
import { AgentComponent } from './components/userComponent/agent/agent.component';
import { AdministratorComponent } from './components/userComponent/administrator/administrator.component';
import { OglasComponent } from './components/oglas/oglas.component';
import { VoziloDetailsDialogComponent } from './components/oglas/vozilo-details-dialog/vozilo-details-dialog.component';
import { MyShopCartItemsComponent } from './components/oglas/my-shop-cart-items/my-shop-cart-items.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    UserComponent,
    AgentComponent,
    AdministratorComponent,
    OglasComponent,
    VoziloDetailsDialogComponent,
    MyShopCartItemsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    AngularMaterialModule,
    FormsModule,
    HttpClientModule
  ],
  entryComponents: [VoziloDetailsDialogComponent],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
