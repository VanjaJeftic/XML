import { MatSnackBar, MatDialog, TransitionCheckState } from '@angular/material';
import { OglasView } from './../../../../models/oglas-view';
import { AuthenticationService } from './../../../../services/authentication.service';
import { OglasService } from './../../../../services/oglas.service';
import { Zahtev } from './../../../../models/zahtev';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RezervacijaDialogComponent } from './rezervacija-dialog/rezervacija-dialog.component';

@Component({
  selector: 'app-vozilo-details',
  templateUrl: './vozilo-details.component.html',
  styleUrls: ['./vozilo-details.component.css']
})
export class VoziloDetailsComponent implements OnInit {

  shopCartItem: Zahtev[] = [];

  oglas: OglasView;
  zahtev:Zahtev = new Zahtev();

  datumPreuzimanja: Date;
  datumPovratka: Date;
  timeFrom: Date;
  timeTo: Date;

  isButtonDisabled = false;

  constructor(private route: ActivatedRoute, private oglasService: OglasService, private authService: AuthenticationService,
              private router: Router, public snackBar: MatSnackBar, private dialog: MatDialog) { }

  ngOnInit() {
    let idOglasa = parseInt(this.route.snapshot.paramMap.get('id'));
    this.oglasService.getOneOglas(idOglasa).subscribe(
      data => {
        console.log(data);
        this.oglas = data;
      }
    );
  }

  onRezervisi(){
    // let monthPreuzimanja = this.datumPreuzimanja.getMonth() + 1;
    // let realDanPreuzimanja;
    // let realMesecPreuzimanja;
    // let danPreuzimanja = this.datumPreuzimanja.getDate();
    // let godinaPreuzimanja = this.datumPreuzimanja.getFullYear();
    // if(danPreuzimanja < 10){
    //   realDanPreuzimanja = '0' + danPreuzimanja;
    // }else {
    //   realDanPreuzimanja = danPreuzimanja;
    // }
    // if(monthPreuzimanja < 10){
    //   realMesecPreuzimanja = '0' + monthPreuzimanja;
    // }else {
    //   realMesecPreuzimanja = monthPreuzimanja;
    // }
    // let preuzimanje = godinaPreuzimanja + '-' + realMesecPreuzimanja + '-' + realDanPreuzimanja + 'T' + this.timeFrom;
    // console.log('Preuzimanje' + preuzimanje);

    // //Sredjivanje datuma povratka

    // let monthPovratka = this.datumPovratka.getMonth() + 1;
    // let realDanPovratka;
    // let realMesecPovratka;
    // let danPovratka = this.datumPovratka.getDate();
    // let godinaPovratka = this.datumPovratka.getFullYear();
    // if(danPovratka < 10){
    //   realDanPovratka = '0' + danPovratka;
    // }else{
    //   realDanPovratka = danPovratka;
    // }
    // if(monthPovratka < 10){
    //   realMesecPovratka = '0' + monthPovratka;
    // }else {
    //   realMesecPovratka = monthPovratka;
    // }
    // let povratak = godinaPovratka + '-' + realMesecPovratka + '-' + realDanPovratka + 'T' + this.timeTo;
    // console.log('Povratak: ' + povratak);
    
    
    //this.zahtev.preuzimanje = preuzimanje;
    //this.zahtev.povratak = povratak;
    this.zahtev.oglas = this.oglas;
    this.zahtev.oglas.id = this.oglas.id;
    console.log(this.zahtev);

    const dialogRef = this.dialog.open(RezervacijaDialogComponent, {data: this.zahtev});
    dialogRef.afterClosed().subscribe(
      result => {
        
      }
    );

    // this.shopCartItem = JSON.parse(window.localStorage.getItem('ShopCartItem'));
    // if(this.shopCartItem){
    //   this.shopCartItem.push(this.zahtev);
    //   window.localStorage.setItem('ShopCartItem', JSON.stringify(this.shopCartItem));
    // }else{
    //   this.shopCartItem = [];
    //   this.shopCartItem.push(this.zahtev);
    //   window.localStorage.setItem('ShopCartItem', JSON.stringify(this.shopCartItem));
    // }

    // this.snackBar.open('Zahtev dodat u korpu!', 'U redu', { duration: 10000 });
    // this.isButtonDisabled = true;
  }

  onOdjaviMe(){
    window.localStorage.clear();
    this.authService.logout();
  }

  onShoppingCart(){
    this.router.navigateByUrl('user/cart');
  }
}
