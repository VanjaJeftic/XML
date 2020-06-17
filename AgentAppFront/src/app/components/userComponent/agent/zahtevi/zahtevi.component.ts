import { MatSnackBar } from '@angular/material';
import { ZahtevService } from './../../../../services/zahtev.service';
import { AuthenticationService } from './../../../../services/authentication.service';
import { Router } from '@angular/router';
import { ZahtevBundleViewDTO } from './../../../../models/zahtev-bundle-view-dto';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-zahtevi',
  templateUrl: './zahtevi.component.html',
  styleUrls: ['./zahtevi.component.css']
})
export class ZahteviComponent implements OnInit {

  zahtevi: ZahtevBundleViewDTO[] = [];

  constructor(private router: Router, private authService: AuthenticationService, private zahtevService: ZahtevService,
    private snackBar: MatSnackBar) { }

  ngOnInit() {
    this.zahtevService.getZahtevi().subscribe(
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

  onPrihvati(data){
    console.log(data);
    this.zahtevService.prihvatiZahtev(data.bundleID).subscribe(
      data => {
        this.snackBar.open('Zahtev prihvacen!', 'U redu', { duration: 10000 });
        location.reload();
      },
      err => {
        alert('Ups, vozilo je vec zauzeto!');
        this.snackBar.open('Ups, vozilo je vec zauzeto za ovaj termin!', 'U redu', { duration: 10000 });
      }
    );
  }

  onUnesiIzvestaj(){
    this.router.navigateByUrl('agent/izvestaj');
  }

  onMojaVozila(){
    this.router.navigateByUrl('agent/vozilo');
  }

  onOdjaviMe(){
    this.authService.logout();
  }
}
