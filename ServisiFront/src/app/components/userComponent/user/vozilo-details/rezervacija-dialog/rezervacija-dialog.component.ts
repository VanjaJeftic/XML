import { MatDialogRef, MAT_DIALOG_DATA, MatSnackBar } from '@angular/material';
import { Zahtev } from './../../../../../models/zahtev';
import { Component, OnInit, Inject } from '@angular/core';

@Component({
  selector: 'app-rezervacija-dialog',
  templateUrl: './rezervacija-dialog.component.html',
  styleUrls: ['./rezervacija-dialog.component.css']
})
export class RezervacijaDialogComponent implements OnInit {

  shopCartItem: Zahtev[] = [];

  zahtev:Zahtev ;

  datumPreuzimanja: Date;
  datumPovratka: Date;
  timeFrom: Date;
  timeTo: Date;

  isButtonDisabled = false;

  constructor(public dialogRef: MatDialogRef<RezervacijaDialogComponent>, @Inject(MAT_DIALOG_DATA) public model : Zahtev,
                    public snackBar: MatSnackBar) {
                      
                      this.zahtev = model;
                     }

  ngOnInit() {
  }

  onRezervisi(){
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
    console.log('Preuzimanje' + preuzimanje);

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
    console.log('Povratak: ' + povratak);
    
    console.log(this.zahtev);

    this.zahtev.preuzimanje = preuzimanje;
    this.zahtev.povratak = povratak;

    this.shopCartItem = JSON.parse(window.localStorage.getItem('ShopCartItem'));
    if(this.shopCartItem){
      this.shopCartItem.push(this.zahtev);
      window.localStorage.setItem('ShopCartItem', JSON.stringify(this.shopCartItem));
    }else{
      this.shopCartItem = [];
      this.shopCartItem.push(this.zahtev);
      window.localStorage.setItem('ShopCartItem', JSON.stringify(this.shopCartItem));
    }

    this.snackBar.open('Zahtev dodat u korpu!', 'U redu', { duration: 10000 });
    this.isButtonDisabled = true;
  }

}
