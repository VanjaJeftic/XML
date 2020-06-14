import { TerminZauzecaDialogComponent } from './termin-zauzeca-dialog/termin-zauzeca-dialog.component';
import { Vozilo } from 'src/app/models/vozilo';
import { Router } from '@angular/router';
import { MatDialog, MatTableDataSource } from '@angular/material';
import { AuthenticationService } from './../../../../services/authentication.service';
import { Component, OnInit } from '@angular/core';
import { VoziloService } from 'src/app/services/vozilo.service';

@Component({
  selector: 'app-moja-vozila',
  templateUrl: './moja-vozila.component.html',
  styleUrls: ['./moja-vozila.component.css']
})
export class MojaVozilaComponent implements OnInit {

  displayedColumns: string[] = ['Klasa', 'Model', 'Marka', 'KM' ,'Zauzece', 'Detaljnije']
  voziloSource;

  constructor(private authService: AuthenticationService, private voziloService: VoziloService,
              private dialog: MatDialog, private router: Router) { }

  ngOnInit() {
    this.voziloService.getVozilaAgenta().subscribe(
      data => {
        console.log(data);
        this.voziloSource = new MatTableDataSource(data);
      }
    );
  }

  onUnesiZauzece(element: number){
    console.log(element);
    const dialogRef = this.dialog.open(TerminZauzecaDialogComponent, {data: element});
    dialogRef.afterClosed().subscribe(
      result => {
        
      }
    );
  }

  onZahtevi(){
    this.router.navigateByUrl('agent/zahtev');
  }

  onUnesiIzvestaj(){
    this.router.navigateByUrl('agent/izvestaj');
  }

  onOdjaviMe(){
    this.authService.logout();
  }

}
