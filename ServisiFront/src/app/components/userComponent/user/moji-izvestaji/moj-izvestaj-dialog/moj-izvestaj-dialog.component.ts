import { OglasService } from './../../../../../services/oglas.service';
import { IzvestajDialogComponent } from './../../../agent/izvestaj/izvestaj-dialog/izvestaj-dialog.component';
import { MatDialogRef, MAT_DIALOG_DATA, MatSnackBar } from '@angular/material';
import { Zahtev } from './../../../../../models/zahtev';
import { Izvestaj } from './../../../../../models/izvestaj';
import { Component, OnInit, Inject } from '@angular/core';

@Component({
  selector: 'app-moj-izvestaj-dialog',
  templateUrl: './moj-izvestaj-dialog.component.html',
  styleUrls: ['./moj-izvestaj-dialog.component.css']
})
export class MojIzvestajDialogComponent implements OnInit {

  zahtev: Zahtev;
  izvestaj: Izvestaj = new Izvestaj();

  constructor(public dialogRef: MatDialogRef<IzvestajDialogComponent>, @Inject(MAT_DIALOG_DATA) public model : any,
                private oglasService: OglasService, private snackBar: MatSnackBar) { 
        this.zahtev = model;
        this.izvestaj.vozilo = this.zahtev.oglas.vozilo.id;
        this.izvestaj.zahtev = this.zahtev.id;
  }

  ngOnInit() {
  }

  onUnesiIzvestaj(){
    this.oglasService.dodajIzvestaj(this.izvestaj).subscribe(
      data => {
        this.snackBar.open('Izvestaj uspesno sacuvan', 'U redu', { duration: 10000 });
      },
      error => {
        this.snackBar.open('Ups, nesto je poslo po zlu. Probajte ponovo kasnije.', 'U redu', { duration: 10000 });
      }
    );
  }

}
