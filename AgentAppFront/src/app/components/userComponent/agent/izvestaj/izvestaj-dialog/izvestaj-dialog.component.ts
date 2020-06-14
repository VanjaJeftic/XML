import { OglasService } from './../../../../../services/oglas.service';
import { MatDialogRef, MAT_DIALOG_DATA, MatSnackBar } from '@angular/material';
import { Izvestaj } from './../../../../../models/izvestaj';
import { Zahtev } from './../../../../../models/zahtev';
import { Component, OnInit, Inject } from '@angular/core';

@Component({
  selector: 'app-izvestaj-dialog',
  templateUrl: './izvestaj-dialog.component.html',
  styleUrls: ['./izvestaj-dialog.component.css']
})
export class IzvestajDialogComponent implements OnInit {

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
