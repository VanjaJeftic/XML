import { IzvestajDialogComponent } from './izvestaj-dialog/izvestaj-dialog.component';
import { ZahtevBundleViewDTO } from './../../../../models/zahtev-bundle-view-dto';
import { MatDialog, MatSnackBar } from '@angular/material';
import { ZahtevService } from './../../../../services/zahtev.service';
import { AuthenticationService } from './../../../../services/authentication.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-izvestaj',
  templateUrl: './izvestaj.component.html',
  styleUrls: ['./izvestaj.component.css']
})
export class IzvestajComponent implements OnInit {

  zahtevi: ZahtevBundleViewDTO[] = [];

  constructor(private router: Router, private authService: AuthenticationService, private zahtevService: ZahtevService,
    private snackBar: MatSnackBar, private dialog: MatDialog) { }

  ngOnInit() {
    this.zahtevService.getZahteviIzvestaj().subscribe(
      data => {
        console.log(data);
        this.zahtevi = data;
        for(let bundle of this.zahtevi){
          for(let zahtev of bundle.bundleZahtevi){
            let godinaPovratak = zahtev.povratak[0];
            let mesecPovratak = zahtev.povratak[1];
            let danPovratak = zahtev.povratak[2];
            let satPovratak = zahtev.povratak[3];
            let minutPovratak = zahtev.povratak[4];

            let godinapreuzimanje = zahtev.preuzimanje[0];
            let mesecpreuzimanje = zahtev.preuzimanje[1];
            let danpreuzimanje = zahtev.preuzimanje[2];
            let satpreuzimanje = zahtev.preuzimanje[3];
            let minutpreuzimanje = zahtev.preuzimanje[4];

            zahtev.povratak = godinaPovratak + '-' + mesecPovratak + '-' + danPovratak + ' ' + satPovratak + ':' + minutPovratak;
            zahtev.preuzimanje = godinapreuzimanje + '-' + mesecpreuzimanje + '-' + danpreuzimanje + ' ' + satpreuzimanje + ':' + minutpreuzimanje;
          
            let godinaSlobodanDo
            if(zahtev.oglas.slobodanDo){
              godinaSlobodanDo = zahtev.oglas.slobodanDo[0];
            }else{
              godinaSlobodanDo = '2020';
            }
            let mesecSlobodanDo
            if(zahtev.oglas.slobodanDo){
              mesecSlobodanDo = zahtev.oglas.slobodanDo[1];
            }else{
              mesecSlobodanDo = '06';
            }
            let danSlobodanDo
            if(zahtev.oglas.slobodanDo){
              danSlobodanDo = zahtev.oglas.slobodanDo[2];
            }else{
              danSlobodanDo = '22';
            }
            let satSlobodanDo
            if(zahtev.oglas.slobodanDo){
              satSlobodanDo = zahtev.oglas.slobodanDo[3];
            }else{
              satSlobodanDo = '22';
            }
            let minutSlobodanDo
            if(zahtev.oglas.slobodanDo){
              minutSlobodanDo = zahtev.oglas.slobodanDo[4];
            }else{
              minutSlobodanDo = '21';
            }

            //SlobodanDo
            let godinaSlobodanOd
            if(zahtev.oglas.slobodanOd[0]){
              godinaSlobodanOd = zahtev.oglas.slobodanOd[0];
            }else{
              godinaSlobodanOd = '2020';
            }
            let mesecSlobodanOd
            if(zahtev.oglas.slobodanOd[0]){
              mesecSlobodanOd = zahtev.oglas.slobodanOd[1];
            }else{
              mesecSlobodanOd = '06';
            }
            let danSlobodanOd
            if(zahtev.oglas.slobodanOd[0]){
              danSlobodanOd = zahtev.oglas.slobodanOd[2];
            }else{
              danSlobodanOd = '22';
            }
            let satSlobodanOd
            if(zahtev.oglas.slobodanOd[0]){
              satSlobodanOd = zahtev.oglas.slobodanOd[3];
            }else{
              satSlobodanOd = '22';
            }
            let minutSlobodanOd
            if(zahtev.oglas.slobodanOd[0]){
              minutSlobodanOd = zahtev.oglas.slobodanOd[4];
            }else{
              minutSlobodanOd = '21';
            }


            let slobodanDo = godinaSlobodanDo + '-' + mesecSlobodanDo + '-' + danSlobodanDo + ' ' + satSlobodanDo + ':' + minutSlobodanDo;
            let SlobodanOd = godinaSlobodanOd + '-' + mesecSlobodanOd + '-' + danSlobodanOd + ' ' + satSlobodanOd + ':' + minutSlobodanOd;
            
            zahtev.oglas.slobodanDoDate = slobodanDo
            zahtev.oglas.slobodanOdDate = SlobodanOd
          }
        }
      }
    );
  }

  onUnesiIzvestaj(zahtev){
    const dialogRef = this.dialog.open(IzvestajDialogComponent, {data: zahtev});
    dialogRef.afterClosed().subscribe(
      result => {
        location.reload();
      }
    );
  }

  onZahtevi(){
    this.router.navigateByUrl('agent/zahtev');
  }

  onMojaVozila(){
   this.router.navigateByUrl('agent/vozilo');
  }

  onOdjaviMe(){
    this.authService.logout();
  }
}
