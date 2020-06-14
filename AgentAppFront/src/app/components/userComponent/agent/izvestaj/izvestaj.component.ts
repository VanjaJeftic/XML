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
