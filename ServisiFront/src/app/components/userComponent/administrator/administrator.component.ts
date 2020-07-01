import { AuthenticationService } from './../../../services/authentication.service';
import { Injectable } from '@angular/core';
import { Component, OnInit } from '@angular/core';

import { MatMenuModule} from '@angular/material/menu';
import { MatDialog } from '@angular/material';
import { DijalogRegistracijaAgentaComponent } from '../dijalog-registracija-agenta/dijalog-registracija-agenta.component';
import { DijalogRegistracijaFirmeComponent } from '../dijalog-registracija-firme/dijalog-registracija-firme.component';

@Component({
  selector: 'app-administrator',
  templateUrl: './administrator.component.html',
  styleUrls: ['./administrator.component.css']
})
export class AdministratorComponent  {

  constructor(public dialog2: MatDialog,public dialog1: MatDialog,private authService: AuthenticationService) { }

  ngOnInit() {
  }

  onOdjaviMe(){
    this.authService.logout();
  }

  public openDialogRegAgenta(){
    const dialogRef = this.dialog1.open(DijalogRegistracijaAgentaComponent, {
      data: { }
    });
    dialogRef.afterClosed().subscribe(result => {

    });
  }

  public openDialogRegFirme(){
    const dialogRef = this.dialog2.open(DijalogRegistracijaFirmeComponent, {
      data: { }
    });
    dialogRef.afterClosed().subscribe(result => {

    });
  }


}
