import { VoziloService } from './../../../../services/vozilo.service';
import { TerminZauzeca } from './../../../../models/termin-zauzeca';
import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA, MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-termin-zauzeca-dialog',
  templateUrl: './termin-zauzeca-dialog.component.html',
  styleUrls: ['./termin-zauzeca-dialog.component.css']
})
export class TerminZauzecaDialogComponent implements OnInit {

  voziloID;

  datumPreuzimanja: Date;
  datumPovratka: Date;
  timeFrom: Date;
  timeTo: Date;

  zauzece: TerminZauzeca = new TerminZauzeca();

  constructor(public dialogRef: MatDialogRef<TerminZauzecaDialogComponent>, @Inject(MAT_DIALOG_DATA) public model : any,
              private voziloService: VoziloService, private snackBar: MatSnackBar) {
    this.voziloID = model.id;
   }

  ngOnInit() {
  }

  onTerminZauzeca(){
    let monthPreuzimanja = this.datumPreuzimanja.getMonth() + 1;
    let realDanPreuzimanja;
    let realMesecPreuzimanja;
    let danPreuzimanja = this.datumPreuzimanja.getDate();
    let godinaPreuzimanja = this.datumPreuzimanja.getFullYear();
    if(danPreuzimanja < 10){
      realDanPreuzimanja = '0' + danPreuzimanja;
    }else {
      realDanPreuzimanja = danPreuzimanja;
    }
    if(monthPreuzimanja < 10){
      realMesecPreuzimanja = '0' + monthPreuzimanja;
    }else {
      realMesecPreuzimanja = monthPreuzimanja;
    }
    let preuzimanje = godinaPreuzimanja + '-' + realMesecPreuzimanja + '-' + realDanPreuzimanja + 'T' + this.timeFrom;

    //Sredjivanje datuma povratka

    let monthPovratka = this.datumPovratka.getMonth() + 1;
    let realDanPovratka;
    let realMesecPovratka;
    let danPovratka = this.datumPovratka.getDate();
    let godinaPovratka = this.datumPovratka.getFullYear();
    if(danPovratka < 10){
      realDanPovratka = '0' + danPovratka;
    }else{
      realDanPovratka = danPovratka;
    }
    if(monthPovratka < 10){
      realMesecPovratka = '0' + monthPovratka;
    }else {
      realMesecPovratka = monthPovratka;
    }
    let povratak = godinaPovratka + '-' + realMesecPovratka + '-' + realDanPovratka + 'T' + this.timeTo;

    this.zauzece.vozilo_id = this.voziloID;
    this.zauzece.zauzetod = preuzimanje;
    this.zauzece.zauzetdo = povratak;

    this.voziloService.zauzmiTerminZauzecaVozila(this.zauzece).subscribe(
      data => {
        this.snackBar.open('Uspesno ste uneli novi termin zauzeca za izabrano vozilo', 'U redu', { duration: 10000 });
      },
      err => {
        this.snackBar.open('Ne mozete zauzeti termin jer je vozilo vec u upotrebi. Kontaktirajte korisnika!', 'U redu', { duration: 10000 });
      }
    );
    
  }

}
