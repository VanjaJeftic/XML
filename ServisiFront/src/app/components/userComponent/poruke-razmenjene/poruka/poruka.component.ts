import { Component, OnInit, Inject } from '@angular/core';
import { Zahtev } from 'src/app/models/zahtev';
import { Poruka } from 'src/app/models/poruka';
import { PorukaService } from 'src/app/services/poruka.service';
import { MatDialogRef, MAT_DIALOG_DATA, MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-poruka',
  templateUrl: './poruka.component.html',
  styleUrls: ['./poruka.component.css']
})
export class PorukaComponent implements OnInit {

  zahtev: Zahtev;
  poruka: Poruka=new Poruka();


  constructor(public dialogRef: MatDialogRef<PorukaComponent>, @Inject(MAT_DIALOG_DATA) public model : any,
            private porukaService: PorukaService, private snackBar: MatSnackBar) {
              console.log("Kod modela"+localStorage.getItem("zahtevId"));
              this.zahtev=model;
             // this.poruka.zahtev=this.zahtev;
             this.poruka.bundle=localStorage.getItem("zahtevId");
              
             }

  ngOnInit() {
  }

  onPosalji(){
    console.log("Sta je ovde ?"+this.poruka.bundle);
    this.poruka.kreator=localStorage.getItem("username");
    this.porukaService.dodajPoruku(this.poruka).subscribe(
      data => {
        this.snackBar.open('Poruka je uspesno poslata', 'U redu', { duration: 10000 });
      },
      error => {
        this.snackBar.open('Ups, nesto je poslo po zlu. Probajte ponovo kasnije.', 'U redu', { duration: 10000 });
      }
    );
  }

}
